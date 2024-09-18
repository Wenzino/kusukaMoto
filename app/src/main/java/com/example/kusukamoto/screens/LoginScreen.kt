import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kusukamoto.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    // Declarando estados para armazenar o texto dos campos
    val emailOrUsername = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val loginError = remember { mutableStateOf<String?>(null) }

    val viewModel: AuthViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logocar),
            contentDescription = "KusukaMoto Logo",
            modifier = Modifier.size(250.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Log In!",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF142D55),
                modifier = Modifier.align(Alignment.CenterStart) // Alinha o texto à esquerda
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Email input
        TextField(
            value = emailOrUsername.value,
            onValueChange = { emailOrUsername.value = it },
            label = { Text(text = "Nome de usuario ou email", color = Color(0xFFFF6F61)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.email),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp) // Ajusta o tamanho do ícone
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color(0xFF142D55)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Password input
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = "Password", color = Color(0xFFFF6F61)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.password),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp) // Ajusta o tamanho do ícone
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color(0xFF142D55)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Forgot Password
        Text(
            text = "Esqueceu a senha?",
            color = Color.Gray,
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Login Button
        Button(
            onClick = {
                if (emailOrUsername.value.isNotEmpty() && password.value.isNotEmpty()) {
                    viewModel.loginWithEmailOrUsername(
                        emailOrUsername.value,
                        password.value,
                        navController
                    ) { error ->
                        loginError.value = error
                        navController.navigate("home")
                    }
                } else {
                    loginError.value = "Todos os campos devem ser preenchidos!"
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E0C6)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "ENTRAR",
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        loginError.value?.let {
            Text(text = it, color = Color.Red, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Link para criar conta
        Text(
            text = "Não tem uma conta?",
            color = Color.Gray
        )
        Text(
            text = "Criar uma conta",
            color = Color(0xFFFF6F61),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                navController.navigate("create_account")
            }
        )
    }
}
