package com.example.kusukamoto.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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

@Composable
fun Iniciar(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)) // Cor de fundo da tela
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Imagem do logo (kusuka.png)
            Image(
                painter = painterResource(id = R.drawable.kusuka), // Certifique-se de que a imagem está na pasta correta
                contentDescription = "Logo da KusukaMoto",
                modifier = Modifier
                    .size(350.dp) // Ajuste o tamanho da imagem
            )

            // Nome do aplicativo
//            Text(
//                text = "KusukaMoto",
//                fontSize = 32.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color(0xFF00A8E0), // Cor similar à da imagem
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 16.dp)
//            )

            // Texto de boas-vindas
            Text(
                text = "Bem-Vindo!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            // Botão "Iniciar"
            Button(
                onClick = {
                    // Navegar para outra tela ao clicar no botão
                    navController.navigate("welcome")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF142D55)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(top = 32.dp)
                    .fillMaxWidth(0.6f) // Ajuste a largura do botão
            ) {
                Text(
                    text = "Iniciar",
                    fontSize = 18.sp,
                    color = Color(0xFF00E0C6) // Cor do texto no botão
                )
            }
        }
    }
}
