package com.jossidfactory.beers.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.jossidfactory.beers.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldBase(text: String, textValue: String, onTextValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = textValue,
        onValueChange = { onTextValueChange(it) },
        trailingIcon = {
            Icon(painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "search")
        },
        label = {
            Text(text = text, color = MaterialTheme.colorScheme.onBackground)
        },
        singleLine = true
    )
}