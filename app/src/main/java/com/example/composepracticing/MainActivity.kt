package com.example.composepracticing

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composepracticing.ui.theme.ComposePracticingTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /**Text*/
//            Greeting(name = "Esraa")

            /**TextField & Button & Snackbar*/
//            GreetingForm()

            /**Card & Box & Image*/
//            val painter = painterResource(R.drawable.img_115)
//            val title = "Author of the book!"
//            val contentDes = "Author of the book"
//            ImageCard(Modifier, painter, title, contentDes)

            /**Row & Column & Spacer & material theming & typography & Remember state & Click Event*/
//            ComposePracticingTheme {
//                Surface {
//                    Conversation(SampleData.conversationSample)
//                }
//            }

            /**Horizontal Pager*/
//            HorizontalPager()
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun Greeting(name: String) {
    Column(
        Modifier
            .background(Color.Blue)
            .fillMaxHeight(0.5f)
            .width(300.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = "Hello $name!")
        Text(text = "Hello $name!")
        Text(text = "Hello $name!")

    }
}

@Composable
fun GreetingForm() {
    var textValueState by remember {
        mutableStateOf("")
    }
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    )
    { content ->
        // Screen content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            content = {
                TextField(
                    value = textValueState,
                    label = {
                        Text("Enter your name")
                    },
                    onValueChange = {
                        textValueState = it
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(15.dp))
                Button(onClick = {
                    scope.launch {
                        snackBarHostState.showSnackbar("Hello $textValueState")
                    }
                }) {
                    Text("Submit")
                }
            }
        )
    }
}

@Composable
fun ImageCard(modifier: Modifier, painter: Painter, title: String, contentDes: String) {
    Card(
        modifier = modifier
            .height(300.dp)
            .fillMaxWidth(0.5f)
            .padding(8.dp)
            .background(Color.Transparent, shape = RoundedCornerShape(15))
    )
    {
        Box {
            Image(
                painter,
                contentDes,
                Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(
                                Color.Transparent,
                                Color.Black
                            ), startY = 700f
                        ), RectangleShape
                    )
            )

            Box(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(title, color = Color.White, fontSize = 20.sp)
            }

        }
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Composable
fun MessageCard(msg: Message) {
    Row {
        Image(
            painter = painterResource(R.drawable.img_115),
            contentDescription = "author image",
            modifier = Modifier
                .size(50.dp)
                .padding(8.dp)
                .clip(shape = CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(
            Modifier.width(
                5.dp
            )
        )
        var isExpanded by remember { mutableStateOf(false) }

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun HorizontalPager() {
    val imgList = listOf<Int>(R.drawable.img_lili1,R.drawable.img_lili2,R.drawable.img_lili3,R.drawable.img_lili4, R.drawable.img_lili6, R.drawable.img_lili7,R.drawable.img_lili8, R.drawable.img_lili9,R.drawable.img_lili1,R.drawable.img_lili2 )
    Card {
        val pagerState = rememberPagerState(pageCount = { 10 })
        HorizontalPager(
            state = pagerState,
            beyondBoundsPageCount = 1
        ) { page ->
            Box (modifier = Modifier.fillMaxWidth().height(400.dp)){
                val painter = painterResource(imgList.get(page))
                Image(
                    painter = painter,
                    contentDescription = "Lili",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(16.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            val coroutineScope = rememberCoroutineScope()
            Button(onClick = {
                coroutineScope.launch {
                    pagerState.scrollToPage(5)
                }
            }, modifier = Modifier.align(Alignment.BottomStart)) {
                Text("Jump to Page 5")
            }
            Button(onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(5)
                }
            }, modifier = Modifier.align(Alignment.BottomEnd)) {
                Text("Animate scroll to Page 5")
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun Preview() {
//    Greeting("Esraa")

//    GreetingForm()

//    val painter = painterResource(R.drawable.img_115)
//    val title = "Author of the book!"
//    val contentDes = "Author of the book"
//    ImageCard(Modifier, painter, title, contentDes)

//    MessageCard(
//        msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!")
//    )


//    ComposePracticingTheme {
//        Surface {
//            Conversation(SampleData.conversationSample)
//        }
//    }

//    HorizontalPager()
}
