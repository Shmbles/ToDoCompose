package com.azrael.to_docompose.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.azrael.to_docompose.R
import com.azrael.to_docompose.components.PriorityDropDown
import com.azrael.to_docompose.data.model.Priority
import com.azrael.to_docompose.ui.theme.LARGE_PADDING
import com.azrael.to_docompose.ui.theme.SMALL_PADDING

@Composable
fun TaskContent(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPriorityChange: (Priority) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(all = LARGE_PADDING)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = stringResource(R.string.task_content_title)) },
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true
        )
        Divider(
            modifier = Modifier.padding(vertical = SMALL_PADDING),
            color = MaterialTheme.colorScheme.background
        )
        PriorityDropDown(priority, onPriorityChange)
        OutlinedTextField(
            modifier = Modifier.fillMaxSize(),
            value = description,
            onValueChange = { onDescriptionChange(it) },
            label = { Text(text = stringResource(R.string.task_content_description)) },
            textStyle = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
@Preview
fun ContentPreview() {
    TaskContent(
        title = "",
        onTitleChange = {},
        description = "",
        onDescriptionChange = {},
        priority = Priority.LOW,
        onPriorityChange = {})
}