package com.example.mvvmcomposablerecyclerview.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar (
    query: String,
    onQueryChanged: (String) -> Unit,
    searchProduct: () -> Unit,
    keyboardController: SoftwareKeyboardController?,
){
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shadowElevation = 8.dp,
        color = MaterialTheme.colorScheme.background,
    ){
        Row(modifier = Modifier.fillMaxWidth()){

            val focusManager = LocalFocusManager.current
            OutlinedTextField(
                value = query,
                onValueChange = {newQuery ->
                    onQueryChanged(newQuery)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                label = { Text("Search") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(onDone = {
                    searchProduct()
                    keyboardController?.hide()
                    focusManager.clearFocus()
                }),
                leadingIcon = {
                    Icon(Icons.Filled.Search, null)
                },
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurface),
                colors = ExposedDropdownMenuDefaults.textFieldColors(MaterialTheme.colorScheme.surface)
            )
        }
    }
}