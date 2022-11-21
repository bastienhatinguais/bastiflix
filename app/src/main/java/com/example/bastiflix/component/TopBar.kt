package com.example.bastiflix.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bastiflix.MainViewModel


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TopBar(navController: NavController) {
    val keyboardController = LocalSoftwareKeyboardController.current

    var text by remember { mutableStateOf("") }
    val viewModel: MainViewModel = viewModel();
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        title = { Text(text = "Bastiflix", color = MaterialTheme.colors.primary) },
        modifier = Modifier.height(70.dp),
        actions = {
            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it;
                },
                placeholder = {
                    Text(
                        "Recherche",
                        color=Color.White
                    )
                },
                maxLines = 1,
                modifier = Modifier.padding(top = 2.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide();
                    when (currentRoute) {
                        "films" -> viewModel.searchMovie(text);
                        "series" -> viewModel.searchSerie(text);
                        "acteurs" -> viewModel.searchPeople(text)
                        else -> { // Note the block
                            throw Exception("La route n'est pas connue.")
                        }
                    }
                }),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    textColor = Color.White
                ),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        tint = Color.White,
                        contentDescription = "Rechercher"
                    )
                },
            )
        })

}
