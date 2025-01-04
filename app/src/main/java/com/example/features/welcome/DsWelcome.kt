package com.example.features.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.features.navigation.Screens
import com.example.features.ui.theme.Cyan
import com.example.features.welcome.models.PagerItems
import com.example.features.welcome.viewmodel.WelcomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun DsWelcome(navController: NavController) {

    val viewModel: WelcomeViewModel = getViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
        ) {
            Text(
                text = buildAnnotatedString {
                    append("Приветствую в ")
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            fontSize = 24.sp
                        )
                    )
                    { append("сборнике приложений") }
                },
                fontSize = 16.sp

            )
        }

        WelcomePager(viewModel.getPagerItems(), navController)

        Box(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .size(width = 400.dp, 1.dp)
                .padding(horizontal = 10.dp)
                .background(Color.LightGray)
        )

        Button(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Cyan),
            onClick = { navController.navigate(Screens.Registration.route) }

        ) {
            Text(
                text = "Создать аккаунт",
                fontSize = 16.sp
            )

        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Cyan),
            onClick = { navController.navigate(Screens.Authorization.route) }
        ) {
            Text(
                text = "Авторизация",
                fontSize = 16.sp
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomePager(
    items: List<PagerItems>,
    navController: NavController
) {

    val pagerState = rememberPagerState(pageCount = { 3 })

    HorizontalPager(
        state = pagerState
    ) { page ->
        Column {
            Image(
                painter = painterResource(items[page].image),
                contentDescription = "image $page",
                alignment = Alignment.Center,
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .clickable { navController.navigate(Screens.Authorization.route) }
            )
            Text(
                text = items[page].title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 40.dp, vertical = 10.dp)
            )
            Text(
                text = items[page].subTitle,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(horizontal = 40.dp)
            )
        }
    }
}