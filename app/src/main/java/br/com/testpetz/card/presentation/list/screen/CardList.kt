package br.com.testpetz.card.presentation.list.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.testpetz.R
import br.com.testpetz.card.domain.entity.Card
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CardList(
    cards: List<Card>,
    navController: NavHostController
) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(cards) { _, item ->
            CardItem(item, navController)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CardItem(
    card: Card,
    navController: NavHostController,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = 2.dp,
        onClick = { navController.navigate("details/${card.name}") }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CardImage(url = card.img ?: "")
            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .weight(1f)
            ) {
                CardTitle(title = card.name ?: "")
                CardDescription(card.text ?: "")
            }
        }

    }
}

@Composable
private fun CardImage(url: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_image_area),
        error = painterResource(R.drawable.ic_image_area),
        contentDescription = stringResource(R.string.list_image_content_description),
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun CardTitle(title: String) {
    Text(
        text = title,
        maxLines = 1,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .padding(horizontal = 8.dp)
    )
}

@Composable
fun CardDescription(description: String) {
    Text(
        text = description.parse().toString(),
        maxLines = 1,
        color = Color.DarkGray,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 4.dp)
    )
}

@Preview
@Composable
fun CardItemPreview() {
    Column {
        CardItem(
            Card(
                name = "Nome do card",
                attack = "6",
                health = "43",
                text = "Texto do card descrevendo alguma coisa",
                type = "Classic"
            ),
            NavHostController(LocalContext.current)
        )
    }
}