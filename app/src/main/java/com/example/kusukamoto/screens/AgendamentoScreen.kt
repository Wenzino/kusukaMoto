import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavBackStackEntry
import com.example.kusukamoto.R
import java.util.*

// Exemplo de dados de serviço
data class Service(
    val name: String,
    val description: String,
    val price: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendamentoScreen(
    navController: NavHostController,
    selectedServices: List<Service>,
    totalPrice: Int
) {
    var selectedDate by remember { mutableStateOf("Escolha uma data") }
    var selectedTime by remember { mutableStateOf("Escolha uma hora") }
    var selectedCarType by remember { mutableStateOf("Tipo de carro") }

    val totalValue = selectedServices.sumOf { it.price }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Agendamento",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF142D55)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrowback),
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5))
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Valor total: $totalPrice MT",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00E0C6),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                selectedServices.forEach { service ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .padding(16.dp)
                    ) {
                        Text(
                            text = service.name.uppercase(Locale.getDefault()),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF142D55),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = service.description,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Escolher uma data
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF00E0C6), shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                        .clickable { /* Abre um seletor de data */ }
                ) {
                    Text(
                        text = selectedDate,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.calendario),
                        contentDescription = "Ícone de calendário",
                        tint = Color.White,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Escolher uma hora
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF00E0C6), shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                        .clickable { /* Abre um seletor de hora */ }
                ) {
                    Text(
                        text = selectedTime,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.alarm),
                        contentDescription = "Ícone de relógio",
                        tint = Color.White,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Selecionar tipo de carro
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF00E0C6), shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                        .clickable { /* Abre um seletor de tipo de carro */ }
                ) {
                    Text(
                        text = selectedCarType,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.cars),
                        contentDescription = "Ícone de carro",
                        tint = Color.White,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Botão de agendar
                Button(
                    onClick = {
                        navController.navigate("payment")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E0C6)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = "Agendar", fontSize = 20.sp, color = Color.White)
                }
            }
        }
    )
}

// Exemplo de lista de serviços
val services = listOf(
    Service("Lavagem Externa", "Limpeza completa da parte externa do veículo, incluindo carroceria, janelas e rodas.", 250),
    Service("Lavagem Interna", "Aspiração e limpeza do interior do veículo, incluindo bancos, tapetes, painéis e vidros internos.", 300),
    Service("Lavagem Detalhada", "Limpeza minuciosa do veículo, abrangendo áreas mais difíceis, como frestas, emblemas, e detalhes pequenos.", 400),
    Service("Polimento Encerramento", "Aplicação de cera e polimento para proteger a pintura e dar brilho à carroceria do veículo.", 500),
    Service("Limpeza de Estofados e Carpetes", "Limpeza profunda de bancos, carpetes e tapetes, utilizando produtos específicos para remover manchas e odores.", 350),
    Service("Lavagem de Motor", "Limpeza do compartimento do motor, removendo sujeira e detritos acumulados, com cuidado para não danificar componentes sensíveis.", 550)
)
