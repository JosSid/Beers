package com.jossidfactory.beers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBeers(value: String, onValueChange: (String) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(64.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        TextField(
            value = value ,
            onValueChange = { onValueChange(it) },
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = value,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        )
        Button(
            onClick = { onValueChange("") },
            modifier = Modifier.fillMaxWidth(),
            enabled = value.isNotEmpty()
        ) {
            Text(text = "Clear")
        }
        ListBeers(value = value)
    }
}