package top.liewyoung.view.mainWindows

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.formdev.flatlaf.FlatLightLaf
import top.liewyoung.view.ColorSystem.AppTheme
import top.liewyoung.view.Stater
import top.liewyoung.view.displayFonts.sharpSans
import javax.swing.JFrame
import javax.swing.SwingUtilities


val poems = listOf<String>(
    "春花秋月何时了？往事知多少。",
    "相见时难别亦难，东风无力百花残。",
    "你若盛开，清风自来。",
    "耽误什么，只等风去。",
    "小楼昨夜又东风，故国不堪回首月明中。",
    "路漫漫其修远兮，吾将上下而求索。",
    "风中有人，雨中有人，雪中有人，雷中有人。",
    "风中谁与共，雨中谁与共，雪中谁与共，雷中谁与共。",
)
/**
 * 获取主页面板
 *
 * @param [frame] 父容器
 * @return [ComposePanel]
 */
fun getHomePanel(frame: JFrame): ComposePanel {
    return ComposePanel().apply {
        setContent {
            var count = remember { mutableStateOf(0) }
            var showDialog = remember { mutableStateOf(false) }

            AppTheme {
                if (showDialog.value) {
                    AlertDialog(
                        onDismissRequest = {
                            showDialog.value = false
                        },
                        title = { Text("你发现了彩蛋", fontWeight = FontWeight.Bold) },
                        text = {
                            if (count.value < 20) {
                                Text(poems[count.value % poems.size], fontWeight = FontWeight.Medium)
                            } else {
                                Text("真没有了别点了", fontWeight = FontWeight.Medium)
                            }
                        },
                        confirmButton = {
                            Button(onClick = {
                                showDialog.value = false
                                count.value++
                            }) {
                                Text("确定", fontWeight = FontWeight.Medium)
                            }
                        }
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.surface)
                        .focusable( true)
                        .clickable(enabled = true, onClick = {})
                        .onKeyEvent { keyEvent ->
                            if (keyEvent.key == Key.W) {
                                showDialog.value = true
                                return@onKeyEvent true
                            }
                            return@onKeyEvent false

                        }
                ) {

                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                "CashFlow",
                                modifier = Modifier.align(Alignment.CenterVertically),
                                fontSize = 48.sp, fontWeight = FontWeight.Bold,
                                fontFamily = sharpSans
                            )

                            Button(onClick = {
                                SwingUtilities.invokeLater {
                                    Stater.main(null)
                                }
                                frame.dispose()
                            }) {
                                Text("开始游戏", fontWeight = FontWeight.Medium)
                            }
                        }

                        Text(
                            "CashFlow是一款基于JavaSwing的桌面游戏，游戏内容由多名作者开发，游戏内容基于《Cash Flow》游戏",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                            fontFamily = sharpSans
                        )
                    }
                }
            }
        }
    }
}


fun main() {
    val frame = JFrame("欢迎")

    SwingUtilities.invokeLater {
        frame.add(getHomePanel(frame))
        frame.setSize(1200, 850)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
        println("启动成功")
    }
}