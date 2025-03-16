package com.azrael.to_docompose.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
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
import com.azrael.to_docompose.ui.theme.LARGE_DP
import com.azrael.to_docompose.ui.theme.MEDIUM_DP

@Composable
fun TaskContent(
    modifier: Modifier = Modifier,
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPriorityChange: (Priority) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(all = LARGE_DP)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = stringResource(R.string.task_content_title)) },
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true
        )
        HorizontalDivider(
            modifier = Modifier.height(MEDIUM_DP),
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