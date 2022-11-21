package com.example.bastiflix.component

import Review
import android.widget.TextView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReviewList(reviews : List<Review>?){
    if (reviews.isNullOrEmpty()) {
        Spinner();
    } else {
        Column() {
            for (review in reviews!!) {
                var textFull by remember { mutableStateOf(false) }

                Card(
                    modifier = Modifier
                        .padding(10.dp)
                    ,
                    border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                    onClick = {
                        textFull = !textFull;
                    }
                ) {
                    Column(Modifier.fillMaxWidth()) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp, horizontal = 5.dp)
                        ) {
                            Row() {
                                if (review.author_details.avatar_path != null) {
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/w200" + review.author_details.avatar_path,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(50.dp)
                                    )
                                }
                                Spacer(Modifier.width(10.dp))
                                Column() {
                                    Text(
                                        review.author,
                                        style = MaterialTheme.typography.h6
                                    )
                                    Text(
                                        review.author_details.username
                                    )
                                }

                            }
                            if (review.author_details.rating != null) {
                                Grade(grade = review.author_details.rating / 2)
                            }
                        }
                        if(textFull){
                            Text(
                                review.content,
                                style = MaterialTheme.typography.body1,
                                overflow = TextOverflow.Visible,
                                modifier = Modifier
                                    .width(350.dp)
                                    .padding(10.dp)
                            );
                        }else{
                            Text(
                                review.content,
                                style = MaterialTheme.typography.body1,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 10,
                                modifier = Modifier
                                    .width(350.dp)
                                    .padding(10.dp)
                            );
                        }

                    }

                }
            }
        }
    }
}