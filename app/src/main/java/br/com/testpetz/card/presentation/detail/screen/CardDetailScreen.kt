package br.com.testpetz.card.presentation.detail.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.testpetz.card.components.scene.Scene
import br.com.testpetz.card.domain.entity.Card
import br.com.testpetz.card.presentation.detail.CardDetailInteraction
import br.com.testpetz.card.presentation.detail.CardDetailViewModel
import br.com.testpetz.card.presentation.list.screen.CardErrorScreen
import br.com.testpetz.card.presentation.list.screen.CardLoading
import br.com.testpetz.card.presentation.list.screen.parse
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.koin.androidx.compose.getViewModel

@Composable
fun CardDetailScreen(
    cardName: String,
    viewModel: CardDetailViewModel = getViewModel()
) {

    LaunchedEffect(Unit) { viewModel.interact(CardDetailInteraction.Opened(cardName)) }
    val state by viewModel.state.collectAsState()

    Scaffold (
        modifier = Modifier.fillMaxSize(),
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Scene(
                async = state.card,
                error = { CardErrorScreen() },
                loading = {
                    CardLoading()
                }
            ) { card ->
                Column(
                    Modifier.padding(vertical = 16.dp)
                ) {
                    CardImageAndInfo(card)
                    CardDescriptionAndInfo(card)
                }
            }
        }
    }
}

@Composable
private fun CardImageAndInfo(card: Card) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp)
    ) {
        Row {
            card.img?.let { url -> CardImage(url, card.name ?: "") }
            Column {
                CardName(card.name ?: "")
                card.type?.let { type ->
                    CardAttribute(
                        stringResource(br.com.testpetz.R.string.detail_label_type),
                        type
                    )
                }
                card.rarity?.let { rarity ->
                    CardAttribute(
                        stringResource(br.com.testpetz.R.string.detail_label_rarity),
                        rarity
                    )
                }
                card.faction?.let { faction -> CardFaction(faction) }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun CardDescriptionAndInfo(card: Card) {
    Card(
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .padding(horizontal = 16.dp)
    ) {
        Column {
            card.descriptionFlavor?.let { desc -> CardDescriptionFlavor(desc) }
            card.text?.let { text -> CardDescription(text) }
            card.cardSet?.let { cardSet -> CardSet(cardSet) }
            card.attack?.let { attack -> CardAttribute(stringResource(br.com.testpetz.R.string.detail_label_attack), attack) }
            card.cost?.let { cost -> CardAttribute(stringResource(br.com.testpetz.R.string.detail_label_cost), cost) }
            card.health?.let { health-> CardAttribute(stringResource(br.com.testpetz.R.string.detail_label_health), health)}
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun CardImage(imageUrl: String, name: String) {
    Box {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(br.com.testpetz.R.drawable.ic_image_area),
            error = painterResource(br.com.testpetz.R.drawable.ic_image_area),
            contentDescription = name,
            contentScale = ContentScale.None,
            modifier = Modifier
                .padding(start = 16.dp)
                .width(100.dp)
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
        )
    }
}

@Composable
private fun CardName(name: String) {
    Text(
        text = name,
        color = Color.DarkGray,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    )
}

@Composable
private fun CardDescriptionFlavor(descriptionFlavor: String) {
    Text(
        text = descriptionFlavor.parse().toString(),
        color = Color.DarkGray,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    )
}

@Composable
private fun CardDescription(description: String) {
    Text(
        text = description.parse().toString(),
        color = Color.DarkGray,
        fontSize = 16.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    )
}

@Composable
private fun CardSet(set: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
    ) {
        Text(
            text = stringResource(br.com.testpetz.R.string.detail_label_set),
            color = Color.DarkGray,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp)
        )

        Text(
            text = set,
            color = Color.DarkGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 16.dp, start = 4.dp, end = 16.dp)
        )
    }
}


@Composable
private fun CardAttribute(label: String, type: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            color = Color.DarkGray,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp)
        )

        Text(
            text = type,
            color = Color.DarkGray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 16.dp, start = 4.dp, end = 16.dp)
        )
    }
}

@Composable
private fun CardFaction(faction: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(br.com.testpetz.R.string.detail_label_faction),
            color = Color.DarkGray,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp)
        )

        Text(
            text = faction,
            color = Color.DarkGray,
            fontSize = 14.sp,
            modifier = Modifier
                .padding(top = 16.dp, start = 4.dp, end = 16.dp)
        )
    }
}

@Composable
@Preview
fun CardDetailsPreview() {
    CardDetailScreen(cardName = "Nome do card")
}

