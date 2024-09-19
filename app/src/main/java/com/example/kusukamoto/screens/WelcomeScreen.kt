import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kusukamoto.R

@Composable
fun WelcomeScreen(navController: NavController) {
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
            modifier = Modifier.size(250.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(0.dp))

        Text(
            text = "Bem-Vindo!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("create_account") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF142D55)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth(0.7f) // Define a largura do botão como 80% da largura disponível
                .fillMaxHeight(0.14f)
                .padding(horizontal = 8.dp) // Adiciona um pouco de padding horizontal
        ) {
            Text(
                text = "Criar uma conta",
                color = Color(0xFF00E0C6)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { navController.navigate("login") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E0C6)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth(0.7f) // Define a largura do botão como 80% da largura disponível
                .fillMaxHeight(0.15f)
                .padding(horizontal = 8.dp) // Adiciona um pouco de padding horizontal
        ) {
            Text(
                text = "Entrar",
                color = Color(0xFF142D55)
            )
        }
    }
}