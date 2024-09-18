import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.example.kusukamoto.R

@Composable
fun ServiceOption(
    serviceName: String,
    iconResId: Int,
    selectedServices: Set<String>,
    onSelect: (String) -> Unit,
    onInfoClick: (String) -> Unit
) {
    val isSelected = selectedServices.contains(serviceName)

    Box(
        modifier = Modifier
            .size(140.dp)  // Tamanho ajustado para se parecer mais com o design
            .background(
                color = if (isSelected) Color(0xFFB3E5FC) else Color(0xFFE1F5FE),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .clickable { onSelect(serviceName) }  // Clique para selecionar
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = serviceName,
                tint = Color.Black,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = serviceName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = Color(0xFF142D55)
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (isSelected) {
                Icon(
                    painter = painterResource(id = R.drawable.tick),
                    contentDescription = "Selected",
                    tint = Color(0xFF142D55),
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        // Botão de informações posicionado no canto superior esquerdo
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(40.dp)  // Ajusta o tamanho do círculo
                .padding(4.dp)
                .background(Color(0xFF142D55), shape = CircleShape)
                .clickable { onInfoClick(serviceName) },  // Clique para navegar
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "i",
                color = Color.White,
                fontSize = 20.sp,  // Ajusta o tamanho da fonte
                fontWeight = FontWeight.Bold
            )
        }
    }
}
