package com.fantasy.sanqianfa.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fantasy.components.theme.CXColor
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import com.fantasy.components.extension.f1c
import com.fantasy.components.theme.CXFont
import com.fantasy.components.widget.CXScaffold

enum class Hexagram(val binary: String) {
    乾("111111"),
    坤("000000"),
    屯("010001"),
    蒙("100010"),
    需("010111"),
    讼("111010"),
    师("000010"),
    比("010000"),
    小畜("110111"),
    履("111011"),
    泰("000111"),
    否("111000"),
    同人("111101"),
    大有("101111"),
    谦("000100"),
    豫("001000"),
    随("011001"),
    蛊("100110"),
    临("000011"),
    观("110000"),
    噬嗑("101001"),
    贲("100101"),
    剥("000001"),
    复("100000"),
    无妄("111001"),
    大畜("100111"),
    颐("100001"),
    大过("011110"),
    坎("010010"),
    离("101101"),
    咸("011100"),
    恒("001110"),
    遁("111100"),
    大壮("001111"),
    晋("101000"),
    明夷("000101"),
    家人("101011"),
    睽("110101"),
    蹇("010100"),
    解("001010"),
    损("100011"),
    益("110001"),
    夬("111110"),
    姤("011111"),
    萃("000110"),
    升("011000"),
    困("010110"),
    井("011010"),
    革("101110"),
    鼎("011101"),
    震("001001"),
    艮("100100"),
    渐("110100"),
    归妹("001011"),
    丰("101100"),
    旅("001101"),
    巽("110110"),
    兑("011011"),
    涣("110010"),
    节("010011"),
    中孚("110011"),
    小过("001100"),
    既济("010101"),
    未济("101010");

    companion object {
        fun fromBinary(binary: String): Hexagram? {
            return values().find { it.binary == binary }
        }
    }
}

@Composable
fun YIN() {
    Row(
        modifier = Modifier.size(36.dp, 3.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Box(
            modifier = Modifier
                .background(CXColor.f1)
                .weight(1f)
                .fillMaxHeight()
        )
        Box(
            modifier = Modifier
                .background(CXColor.f1)
                .weight(1f)
                .fillMaxHeight()
        )
    }
}

@Composable
fun YANG() {
    Box(
        modifier = Modifier
            .background(CXColor.f1)
            .size(36.dp, 3.dp)
    )
}

@Composable
fun SQGua(hexagram: Hexagram) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        // Reverse and iterate to render from bottom to top
        hexagram.binary.reversed().forEach { digit ->
            when (digit) {
                '0' -> YIN()
                '1' -> YANG()
            }
        }
        Text(text = hexagram.name, style = CXFont.f3.v1.f1c)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun SQGuaPreview() {
    CXScaffold(topBar = {}) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.padding(16.dp),
        ) {
            item(span = { GridItemSpan(4) }) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    YIN()
                    YANG()
                }
            }
            items(Hexagram.entries) { hexagram ->
                Column(
                    modifier = Modifier.padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    SQGua(hexagram)
                    Text(
                        text = hexagram.name,
                        style = CXFont.f1.v1.f1c,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}