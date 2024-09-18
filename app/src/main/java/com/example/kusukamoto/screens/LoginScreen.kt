import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, onGoogleSignInClick: () -> Unit) {
    // Declarando estados para armazenar o texto dos campos
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

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
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Log In!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF142D55)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Email input
        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text(text = "Email") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.email),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Black
                )
            },
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

        // Password input
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = "Password") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.password),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Black
                )
            },
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

        // Forgot Password
        Text(
            text = "Esqueceu a senha?",
            color = Color.Gray,
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(22.dp))

        // Login Button
        Button(
            onClick = {
                if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
                    if (email.value == "muradelobo@gmail.com" && password.value == "123456") {
                        navController.navigate("home")
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E0C6)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "ENTRAR", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Google Sign-In Button with Icon and Light Gray Background
        Button(
            onClick = { onGoogleSignInClick() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDDDDDD)),  // Cor cinza claro
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google Sign-In",
                modifier = Modifier.size(20.dp),
                tint = Color.Unspecified  // Garante que o ícone mantenha suas cores originais
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Continuar com Google", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Link para criar conta
        Text(text = "Não tem uma conta?", color = Color.Gray)
        Text(
            text = "Criar uma conta",
            color = Color(0xFFFF6F61),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { navController.navigate("create_account") }
        )
    }
}