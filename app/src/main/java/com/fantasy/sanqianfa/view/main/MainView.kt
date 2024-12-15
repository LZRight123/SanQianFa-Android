package com.fantasy.sanqianfa.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fantasy.components.base.BaseScreen
import com.fantasy.components.base.BaseViewModel
import com.fantasy.components.extension.compose.CXPaddingValues
import com.fantasy.components.extension.compose.Icon
import com.fantasy.components.extension.compose.addHazeContent
import com.fantasy.components.extension.compose.addTagBack
import com.fantasy.components.extension.mockImage
import com.fantasy.components.extension.randomString
import com.fantasy.components.extension.toStringFormat
import com.fantasy.components.theme.CXColor
import com.fantasy.components.theme.CXFont
import com.fantasy.components.theme.CXMutableColors
import com.fantasy.components.tools.Apphelper
import com.fantasy.components.tools.UIImage
import com.fantasy.components.tools.openAlbum
import com.fantasy.components.tools.rememberLauncherUIImage
import com.fantasy.components.widget.CXButton
import com.fantasy.components.widget.CXCoilImage
import com.fantasy.components.widget.CXScaffold
import com.fantasy.components.widget.CXSheetScaffold
import com.fantasy.sanqianfa.R
import com.fantasy.sanqianfa.api.HistoryEvent
import com.fantasy.sanqianfa.api.PublicAPI
import com.fantasy.sanqianfa.api.networking.Networking
import com.fantasy.sanqianfa.view.login.LoginMainView
import dev.funkymuse.compose.core.ifFalse
import dev.funkymuse.compose.core.ifTrue
import dev.funkymuse.compose.core.plus
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainViewModel : BaseViewModel() {

}

class MainView : BaseScreen() {
    @Composable
    override fun body() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "MainView", style = CXFont.f1.v1)
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun Preview() {
        MainView().Content()
    }
}
