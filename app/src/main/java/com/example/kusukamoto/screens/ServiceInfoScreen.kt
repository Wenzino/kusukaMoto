import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kusukamoto.R
//import com.example.kusukamoto.screens.ServiceInfo

@Composable
fun ServiceInfoScreen(navController: NavHostController, serviceInfo: Service) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Linha para o ícone de voltar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrowback),
                    contentDescription = "Voltar para Home",
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
            }

            // Nome do serviço
            Text(
                text = serviceInfo.name,
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF142D55),
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )

            // Preço do serviço
            Text(
                text = "Preço: ${serviceInfo.price}"+"MT",
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF00E0C6),
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Separador (linha horizontal)
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(bottom = 16.dp)
                    .background(Color(0xFF142D55), shape = RoundedCornerShape(1.dp))
                    .size(2.dp)
            )

            // Caixa de descrição do serviço
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(16.dp))
                    .padding(20.dp)
            ) {
                Column {
                    Text(
                        text = "Descrição",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF142D55),
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                    Text(
                        text = serviceInfo.description,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }
            }

            // Botão "Agendar"
            Button(
                onClick = {
                    navController.navigate("booking/${serviceInfo.name}")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E0C6)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(1f)
            ) {
                Text(
                    text = "Agendar",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}
