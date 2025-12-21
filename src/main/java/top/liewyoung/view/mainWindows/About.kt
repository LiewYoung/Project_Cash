package top.liewyoung.view.mainWindows

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.formdev.flatlaf.FlatLightLaf
import top.liewyoung.view.ColorSystem.AppTheme
import top.liewyoung.view.Stater
import javax.swing.JFrame
import javax.swing.SwingUtilities


/**
 * 获取关于面板
 * @return [ComposePanel]
 */
fun getAboutPanel(): ComposePanel {
    return ComposePanel().apply {
        setContent {
            AppTheme {

                Box(
                    modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp) ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("关于", fontWeight = FontWeight.Bold, fontSize = 32.sp)

                        Spacer(modifier = Modifier.size(16.dp))

                        cardFactory(
                            name = "LiewYoung",
                            icon = painterResource("Avstar.jpg")
                        )

                        cardFactory(
                            name = "刘瑞翔",
                            icon = painterResource("Avstar_1.jpg")
                        )

                        cardFactory(
                            name = "Syrnaxei",
                            icon = painterResource("Avstar_2.jpg")
                        )

                        Button(content = {Text("刷新地图", fontWeight = FontWeight.Medium)}, onClick = { Stater.map.refreshMap()})

                    }


                }
            }
        }
    }
}

/**
 * 获取个人介绍卡片
 *
 * @param [name] 名称
 * @param [desc] 描述
 * @param [icon] 图标
 */
@Composable
fun cardFactory(name: String,
                desc: String = "North China University of Water Resources and Electric Power",
                icon: Painter){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(painter = icon, contentDescription = null, modifier = Modifier.size(64.dp).clip(CircleShape))
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ){
            Text(name, fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Text(desc, fontWeight = FontWeight.Medium,fontSize = 16.sp)
        }
    }
}

fun main() {
    val frame = JFrame("Compose 测试窗口")
    FlatLightLaf.setup()
    SwingUtilities.invokeLater {
        frame.add(getAboutPanel())
        frame.setSize(1200, 850)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
        println("启动成功")
    }
}