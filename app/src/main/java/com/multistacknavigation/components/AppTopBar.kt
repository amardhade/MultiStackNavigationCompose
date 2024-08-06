package com.multistacknavigation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppTopBar(
    titles: List<String>,
    navigateTo: (route: String) -> Unit
) {

    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        titles.forEachIndexed { index, title ->
            Text(
                text = title,
                fontSize = 16.sp,
                modifier = Modifier
                    .clickable { navigateTo(title.lowercase()) }
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(corner = CornerSize(8.dp))
                    )
                    .padding(all = 8.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))

        }


//        Text(
//            text = "Tab 2",
//            fontSize = 16.sp,
//            modifier = Modifier
//                .clickable { navigateTo(Routes.TAB2) }
//                .border(
//                    width = 1.dp,
//                    color = Color.Black,
//                    shape = RoundedCornerShape(corner = CornerSize(8.dp))
//                )
//                .padding(all = 8.dp)
//        )
//        Spacer(modifier = Modifier.size(8.dp))
//        Text(
//            text = "Tab 3",
//            fontSize = 16.sp,
//            modifier = Modifier
//                .clickable { navigateTo(Routes.TAB3) }
//                .border(
//                    width = 1.dp,
//                    color = Color.Black,
//                    shape = RoundedCornerShape(corner = CornerSize(8.dp))
//                )
//                .padding(all = 8.dp)
//        )
    }
}

