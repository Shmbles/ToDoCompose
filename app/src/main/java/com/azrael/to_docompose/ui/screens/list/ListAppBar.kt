package com.azrael.to_docompose.ui.screens.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.azrael.to_docompose.R
import com.azrael.to_docompose.components.DisplayAlertDialog
import com.azrael.to_docompose.components.PriorityItem
import com.azrael.to_docompose.data.model.Priority
import com.azrael.to_docompose.ui.theme.LARGE_DP
import com.azrael.to_docompose.ui.theme.MEDIUM_DP
import com.azrael.to_docompose.ui.theme.TOP_APP_BAR_HEIGHT
import com.azrael.to_docompose.ui.viewmodel.SharedViewModel
import com.azrael.to_docompose.util.Action
import com.azrael.to_docompose.util.SearchAppBarState

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel, searchAppBarState: SearchAppBarState, searchTextState: String
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(
                onSearchClicked = {
                    sharedViewModel.updateAppBarState(SearchAppBarState.OPENED)
                },
                onSortClicked = { sharedViewModel.persistSortState(it) },
                onDeleteAllConfirmed = { sharedViewModel.updateAction(Action.DELETE_ALL) })
        }

        else -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = { newText ->
                    sharedViewModel.updateSearchText(newText)
                },
                onCloseClicked = {
                    sharedViewModel.updateAppBarState(SearchAppBarState.CLOSED)
                    sharedViewModel.updateSearchText("")
                },
                onSearchClicked = {
                    sharedViewModel.searchDatabase(searchQuery = it)
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit, onDeleteAllConfirmed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_bar_title),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        actions = { ListAppBarActions(onSearchClicked, onSortClicked, onDeleteAllConfirmed) })
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit, onDeleteAllConfirmed: () -> Unit
) {
    var openDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_all_task),
        message = stringResource(id = R.string.delete_all_task_confirmation),
        openDialog = openDialog,
        closeDialog = { openDialog = false },
        onYesClicked = { onDeleteAllConfirmed() }
    )

    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllAction(onDeleteAllConfirmed = { openDialog = true })
}

@Composable
fun SearchAction(onSearchClicked: () -> Unit) {
    IconButton(onClick = { onSearchClicked() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(id = R.string.app_bar_content_description_search_tasks),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun SortAction(onSortClicked: (Priority) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_filter_list),
            contentDescription = stringResource(id = R.string.app_bar_content_description_sort_tasks),
            tint = MaterialTheme.colorScheme.onPrimary
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            Priority.entries.toTypedArray().slice(setOf(0, 2, 3)).forEach { priority ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onSortClicked(priority)
                    },
                    text = { PriorityItem(priority) })
            }
        }
    }
}

@Composable
fun DeleteAllAction(onDeleteAllConfirmed: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_menu),
            contentDescription = stringResource(id = R.string.app_bar_content_description_delete_all_tasks),
            tint = MaterialTheme.colorScheme.onPrimary
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = {
                Text(
                    modifier = Modifier.padding(start = LARGE_DP),
                    text = stringResource(id = R.string.app_bar_content_description_delete_all_tasks),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }, onClick = {
                expanded = false
                onDeleteAllConfirmed()
            })
        }
    }
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        shadowElevation = MEDIUM_DP,
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(0.5f),
                    text = stringResource(id = R.string.app_bar_content_description_search_tasks),
                    color = Color.White
                )
            },
            textStyle = TextStyle(fontSize = MaterialTheme.typography.bodyMedium.fontSize),
            singleLine = true,
            leadingIcon = {
                IconButton(modifier = Modifier.alpha(0.38f), onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(id = R.string.app_bar_content_description_search_tasks),
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty()) {
                        onTextChange("")
                    } else {
                        onCloseClicked()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(id = R.string.app_bar_content_description_search_tasks),
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked(text) })
        )
    }
}

@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(onSearchClicked = {}, onSortClicked = {}, onDeleteAllConfirmed = {})
}

@Composable
@Preview
private fun SearchAppBarPreview() {
    SearchAppBar(text = "Search", onTextChange = {}, onCloseClicked = {}, onSearchClicked = {})
}