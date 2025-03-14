import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kusukamoto.R
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: AuthViewModel = viewModel()
    val userName = remember { mutableStateOf("Carregando") }
    val userPhotoUrl = remember { mutableStateOf("") }
    val selectedServices = remember { mutableStateOf(setOf<String>()) }

    // Fetch dados do usuário ao carregar a tela
    LaunchedEffect(Unit) {
        viewModel.getUserData { name, photoUrl ->
            userName.value = name
            userPhotoUrl.value = photoUrl
        }
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->

        // Permite o scroll vertical
        val scrollState = rememberScrollState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(paddingValues)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .align(Alignment.TopCenter)
            ) {
                drawCircle(
                    color = Color(0xFF00E0C6),
                    radius = size.width * 1.5f,
                    center = Offset(x = size.width / 2, y = -size.width / 0.95f)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState), // Adiciona scroll aqui
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.notification),
                        contentDescription = "Notification Icon",
                        tint = Color(0xFF142D55),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    // Contém o fundo amarelo e a imagem circular
                    Box(
                        modifier = Modifier
                            .size(50.dp) // Tamanho do círculo
                            .background(Color(0xFFFFCC33), shape = CircleShape) // Fundo amarelo
                    ) {
                        AsyncImage(
                            model = userPhotoUrl.value.ifEmpty { R.drawable.usericon },
                            contentDescription = "User Avatar",
                            contentScale = ContentScale.Crop, // Preenche o círculo
                            modifier = Modifier
                                .fillMaxSize() // Preenche todo o tamanho da Box
                                .clip(CircleShape) // Corta a imagem no formato circular
                        )
                    }
                }

                Text(
                    text = "Olá,\n${userName.value}",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF142D55),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                Text(
                    text = "Os nossos serviços",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF142D55),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 45.dp, bottom = 16.dp)
                )
                Text(
                    text = "Marque o/os serviço/s pretendido/s",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF142D55),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 0.dp, bottom = 16.dp)
                )


                val services = listOf(
                    "Lavagem Externa" to R.drawable.exterior,
                    "Lavagem Interna" to R.drawable.interna,
                    "Lavagem Detalhada" to R.drawable.detalhada,
                    "Polimento Encerramento" to R.drawable.polimento,
                    "Limpeza Estofados Carpetes" to R.drawable.carpete,
                    "Lavagem Motor" to R.drawable.motor
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (i in services.indices step 2) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            ServiceOption(
                                serviceName = services[i].first,
                                iconResId = services[i].second,
                                selectedServices = selectedServices.value,
                                onSelect = { selectedService ->
                                    selectedServices.value = selectedServices.value.toMutableSet().apply {
                                        if (contains(selectedService)) remove(selectedService)
                                        else add(selectedService)
                                    }
                                },
                                onInfoClick = { serviceName ->
                                    navController.navigate("serviceInfo/${serviceName}")
                                }
                            )
                            if (i + 1 < services.size) {
                                ServiceOption(
                                    serviceName = services[i + 1].first,
                                    iconResId = services[i + 1].second,
                                    selectedServices = selectedServices.value,
                                    onSelect = { selectedService ->
                                        selectedServices.value = selectedServices.value.toMutableSet().apply {
                                            if (contains(selectedService)) remove(selectedService)
                                            else add(selectedService)
                                        }
                                    },
                                    onInfoClick = { serviceName ->
                                        navController.navigate("serviceInfo/${serviceName}")
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                val totalPrice = remember { mutableStateOf(0) }
                Button(
                    onClick = {
                        val selectedServicesList = selectedServices.value.toList()
                        val total = selectedServicesList.sumOf { service ->
                            when (service) {
                                "Lavagem Externa" -> 150
                                "Lavagem Interna" -> 250
                                "Lavagem Detalhada" -> 350
                                "Polimento Encerramento" -> 450
                                "Limpeza Estofados Carpetes" -> 200
                                "Lavagem Motor" -> 550
                                else -> 0
                            }.toLong()
                        }.toInt()
                        totalPrice.value = total

                        // Passando os serviços selecionados e o total para a tela de agendamento
                        val selectedServicesString = selectedServices.value.joinToString(",")
                        Log.d(
                            "Navigation",
                            "Navigating to agendamento with: $selectedServicesString and totalPrice: ${totalPrice.value}"
                        )

                        val encodedServices = URLEncoder.encode(selectedServicesString, "UTF-8")
                        navController.navigate("agendamento/$encodedServices?totalPrice=${totalPrice.value}")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E0C6)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(60.dp)
                ) {
                    Text(
                        text = "Avançar",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}