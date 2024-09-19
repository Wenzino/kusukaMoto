import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel() : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun firebaseAuthWithGoogle(idToken: String, navController: NavController) {
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            auth.signInWithCredential(credential).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        saveUserDataToFirestore(it)
                        navController.navigate("home")
                    }
                } else {
                    Log.e("Auth", "Erro de autenticação", task.exception)
                }
            }
        } catch (e: Exception) {
            Log.e("GoogleSignIn", "Falha na autenticação com Google", e)
        } catch (e: Exception) {
            Log.e("GoogleSignIn", "Erro inesperado", e)
        }
    }

    private fun saveUserDataToFirestore(user: FirebaseUser) {
        val userData = hashMapOf(
            "uid" to user.uid,
            "name" to user.displayName,
            "email" to user.email,
            "photoUrl" to user.photoUrl.toString()
        )

        firestore.collection("users").document(user.uid)
            .set(userData)
            .addOnSuccessListener {
                Log.d("Firestore", "Dados do usuário armazenados com sucesso!")
            }.addOnFailureListener { e ->
                Log.e("Firestore", "Erro ao armazenar dados no Firestore", e)
            }
    }

    fun createUserWithEmailAndPassword(email: String, password: String, name: String, contact: String, navController: NavController) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                user?.let {
                    saveUserDataToFirestore(it, name, contact)
                    navController.navigate("home")
                }
            } else {
                try {
                    throw task.exception ?: Exception("Erro desconhecido")
                } catch (e: FirebaseAuthUserCollisionException) {
                    Log.e("Auth", "Conta já existe", e)
                } catch (e: Exception) {
                    Log.e("Auth", "Erro ao cirar a conta", e)
                }
            }
        }
    }

    // Nova função sobrecarregada que aceita nome e contato
    private fun saveUserDataToFirestore(user: FirebaseUser, name: String, contact: String) {
        val userData = hashMapOf(
            "uid" to user.uid,
            "name" to name,
            "email" to user.email,
            "contact" to contact,
            "photoUrl" to user.photoUrl.toString()
        )

        firestore.collection("users").document(user.uid)
            .set(userData)
            .addOnSuccessListener {
                Log.d("Firestore", "Dados do usuário armazenados com sucesso!")
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Erro ao armazenar dados no Firestore", e)
            }
    }

    fun getUserData(onResult: (String) -> Unit) {
        val user = auth.currentUser
        if (user != null) {
            firestore.collection("users").document(user.uid).get()
                .addOnSuccessListener { document ->
                    val name = document.getString("name") ?: "Usuário"
                    onResult(name)
                }
                .addOnFailureListener {
                    onResult("Erro ao carregar nome")
                }
        } else {
            onResult("Usuário não logado")
        }
    }

    fun saveAgendamento(userId: String, services: List<Service>, totalPrice: Int, date: String, time: String) {
        val agendamentoData = mapOf(
            "userId" to userId,
            "services" to services.map { it.name },
            "totalPrice" to totalPrice,
            "date" to date,
            "time" to time,
            "status" to "pending"  // O admin pode aceitar ou rejeitar
        )

        firestore.collection("agendamentos")
            .add(agendamentoData)
            .addOnSuccessListener {
                Log.d("Firestore", "Agendamento salvo com sucesso!")
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Erro ao salvar o agendamento", e)
            }
    }

    fun loginWithEmailOrUsername(input: String, password: String, navController: NavController, onError: (String) -> Unit) {
        val queryField = if (android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches()) {
            "email"  // Se for email
        } else {
            "name"   // Se for username
        }

        firestore.collection("users")
            .whereEqualTo(queryField, input)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val userDoc = documents.documents[0]
                    if (userDoc.exists() && userDoc.getString("password") == password) {
                        navController.navigate("home")
                    } else {
                        onError("Credenciais inválidas")
                    }
                } else {
                    onError("Usuário não encontrado")
                }
            }
            .addOnFailureListener {
                onError("Erro ao buscar dados no Firestore")
            }
    }
}
