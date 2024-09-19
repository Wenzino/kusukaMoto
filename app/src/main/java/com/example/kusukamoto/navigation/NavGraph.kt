package com.example.kusukamoto.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import navArgument
import androidx.navigation.NavType
//import com.example.kusukamoto.screens.Service
import Service
import ServiceInfoScreen
import com.example.kusukamoto.screens.Iniciar
import HomeScreen
import LoginScreen
import CreateAccountScreen
import AgendamentoScreen
import AuthViewModel
import ProfileScreen
import WelcomeScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.navArgument
import com.example.kusukamoto.MainActivity
import com.example.kusukamoto.screens.EditProfileScreen
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController) {

    val viewModel: AuthViewModel = viewModel()

    NavHost(navController = navController, startDestination = "iniciar") {
        // Tela de boas-vindas
        composable("welcome") {
            WelcomeScreen(navController = navController)
        }

        // Tela de login
        composable("login") {
            val context = LocalContext.current as MainActivity
            LoginScreen(navController = navController, onGoogleSignInClick = {
                context.initiateGoogleSignIn()
            })
        }
        // Tela de criação de conta
        composable("create_account") { CreateAccountScreen(navController = navController) }

        // Tela inicial (Home)
        composable("home") { HomeScreen(navController = navController) }

        // Tela inicial (Iniciar)
        composable("iniciar") { Iniciar(navController = navController) }

        // Tela de informações do serviço com argumento
        composable(
            "serviceInfo/{serviceName}",
            arguments = listOf(navArgument("serviceName") { type = NavType.StringType })
        ) { backStackEntry ->
            val serviceName = backStackEntry.arguments?.getString("serviceName")

            val serviceInfo = when (serviceName) {
                "Lavagem Externa" -> Service(
                    name = "LAVAGEM EXTERNA",
                    price = 1500,
                    description = "Este serviço envolve a limpeza completa da parte externa do veículo. " +
                            "O processo inclui a remoção de sujeira, lama, poeira e outros resíduos da carroceria, janelas, e rodas. " +
                            "Produtos de limpeza específicos são aplicados, seguidos de um enxágue com água sob pressão para garantir " +
                            "que a superfície do carro fique limpa e brilhante."
                )
                "Lavagem Interna" -> Service(
                    name = "LAVAGEM INTERNA",
                    price = 2500,
                    description = "A lavagem interna foca na limpeza do interior do veículo. Isso inclui a " +
                            "aspiração de sujeira e poeira dos bancos, tapetes e porta-malas, além de limpar painéis, console central, " +
                            "vidros internos, e áreas de difícil acesso. Produtos de limpeza especializados são usados para garantir " +
                            "que as superfícies internas fiquem livres de manchas, odores e sujeira acumulada."
                )
                "Lavagem Detalhada" -> Service(
                    name = "LAVAGEM DETALHADA",
                    price = 3500,
                    description = "Este serviço é uma limpeza minuciosa que vai além da lavagem básica. " +
                            "Abrange a limpeza de áreas mais difíceis de alcançar, como frestas, emblemas, rodas, e molduras. " +
                            "Detalhes específicos do veículo são cuidadosamente tratados, garantindo uma limpeza profunda e completa."
                )
                "Polimento Encerramento" -> Service(
                    name = "POLIMENTO E ENCERRAMENTO",
                    price = 1050,
                    description = "Este serviço envolve a aplicação de produtos de polimento e cera na carroceria do veículo. " +
                            "O polimento remove pequenas imperfeições na pintura, como arranhões leves e marcas de oxidação, enquanto a cera " +
                            "protege a pintura e dá um brilho intenso à superfície, fazendo o carro parecer novo."
                )
                "Limpeza Estofados Carpetes" -> Service(
                    name = "LIMPEZA DE ESTOFADOS E CARPETES",
                    price = 1300,
                    description = "Aqui, o foco é a limpeza profunda dos bancos e carpetes do veículo. Usam-se produtos específicos para " +
                            "remover manchas, sujeira e odores, revitalizando o interior do carro. Este serviço é ideal para quem deseja " +
                            "manter o interior do veículo com uma aparência fresca e limpa."
                )
                "Lavagem Motor" -> Service(
                    name = "LAVAGEM DE MOTOR",
                    price = 550,
                    description = "A lavagem do motor envolve a limpeza cuidadosa do compartimento do motor, removendo sujeira, " +
                            "óleo e detritos acumulados. Produtos e técnicas especiais são usados para garantir que o motor seja limpo " +
                            "sem danificar componentes sensíveis, como fios e conexões elétricas."
                )
                else -> Service(
                    name = "Serviço Não Encontrado",
                    price = 0,
                    description = "Informações sobre este serviço não estão disponíveis."
                )
            }

            // Passar a instância de serviceInfo para a tela
            ServiceInfoScreen(navController = navController, serviceInfo = serviceInfo)
        }

        // Tela de agendamento com argumento
        composable(
            "agendamento/{selectedServices}/{totalPrice}",
            arguments = listOf(
                navArgument("selectedServices") { type = NavType.StringType },
                navArgument("totalPrice") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val selectedServicesString = backStackEntry.arguments?.getString("selectedServices") ?: ""
            val totalPrice = backStackEntry.arguments?.getInt("totalPrice") ?: 0
            val selectedServices = selectedServicesString.split(",").map { serviceName ->
                // Substitua por uma busca de dados real se necessário
                Service(serviceName, "Descrição do $serviceName", 0)
            }
            AgendamentoScreen(navController = navController, selectedServices = selectedServices, totalPrice = totalPrice)
        }

        // Tela de edição de perfil
        composable("edit_profile") {
            EditProfileScreen(navController = navController)
        }

        composable("userProfile") {
            ProfileScreen(
                navController = navController,
                onAccountClick = {
                    navController.navigate("account")
                },
                onEditProfileClick = {
                    navController.navigate("edit_profile")
                },
                onLogoutClick = {
                    // Lógica para logout
                    viewModel.logout(navController)
                }
            )
        }

    }
}
