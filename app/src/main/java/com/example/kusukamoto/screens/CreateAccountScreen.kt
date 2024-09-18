import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kusukamoto.R
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(navController: NavController) {

    val viewModel: AuthViewModel = viewModel()
    // Declarando estados para armazenar o texto dos campos
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val contact = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.kusuka),
            contentDescription = "KusukaMoto Logo",
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Crie a sua conta",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF142D55)
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Nome
        TextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text(text = "Nome") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color(0xFF142D55),
                unfocusedIndicatorColor = Color.Gray,
                focusedContainerColor = Color.White,  // Fundo branco para evitar interferência do tema escuro
                unfocusedContainerColor = Color.White, // Fundo branco quando desfocado
                focusedTextColor = Color.Black,  // Texto preto em foco
                unfocusedTextColor = Color.Black, // Texto preto quando desfocado
                focusedLabelColor = Color(0xFFFF6F61),  // Label focado
                unfocusedLabelColor = Color.Gray  // Label desfocado
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Email
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color(0xFF142D55),
                unfocusedIndicatorColor = Color.Gray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedLabelColor = Color(0xFFFF6F61),
                unfocusedLabelColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Password
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color(0xFF142D55),
                unfocusedIndicatorColor = Color.Gray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedLabelColor = Color(0xFFFF6F61),
                unfocusedLabelColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password
        TextField(
            value = confirmPassword.value,
            onValueChange = { confirmPassword.value = it },
            label = { Text(text = "Confirme a Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color(0xFF142D55),
                unfocusedIndicatorColor = Color.Gray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedLabelColor = Color(0xFFFF6F61),
                unfocusedLabelColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Contact
        TextField(
            value = contact.value,
            onValueChange = { contact.value = it },
            label = { Text(text = "Contacto") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color(0xFF142D55),
                unfocusedIndicatorColor = Color.Gray,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedLabelColor = Color(0xFFFF6F61),
                unfocusedLabelColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (password.value == confirmPassword.value) {
                    viewModel.createUserWithEmailAndPassword(
                        email.value,
                        password.value,
                        name.value,
                        contact.value,
                        navController
                    )
                } else {
                    Log.e("Auth", "Senhas não coincidem")
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF142D55)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "CRIAR",
                color = Color(0xFF00E0C6)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Já tem uma conta?", color = Color.Gray)
        Text(
            text = "Entrar",
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                navController.navigate("login")
            }
        )
    }
}
