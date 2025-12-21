package top.liewyoung.view.displayFonts

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font


// 创建自定义字体族
val sharpSans = FontFamily(
    Font(resource = "fonts/AlibabaPuHuiTi-3-55-RegularL3.otf", weight = FontWeight.Normal, style = FontStyle.Normal),
    Font(resource = "fonts/AlibabaPuHuiTi-3-65-Medium.otf", weight = FontWeight.Medium, style = FontStyle.Normal),
    Font(resource = "fonts/AlibabaPuHuiTi-3-85-Bold.otf", weight = FontWeight.Bold, style = FontStyle.Normal)
)
