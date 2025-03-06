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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azrael.to_docompose.R
import com.azrael.to_docompose.components.PriorityItem
import com.azrael.to_docompose.data.model.Priority
import com.azrael.to_docompose.ui.theme.LARGE_PADDING
import com.azrael.to_docompose.ui.theme.TOP_APP_BAR_HEIGHT
import com.azrael.to_docompose.ui.theme.Typography
import com.azrael.to_docompose.ui.theme.focusedContainerColor
import com.azrael.to_docompose.ui.theme.topAppBarBackgroundColor
import com.azrael.to_docompose.ui.theme.topAppBarContentColor
import com.azrael.to_docompose.ui.theme.unfocusedContainerColor
import com.azrael.to_docompose.ui.viewmodel.SharedViewModel
import com.azrael.to_docompose.util.SearchAppBarState
import com.azrael.to_docompose.util.TrailingIconState

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel, searchAppBarState: SearchAppBarState, searchTextState: String
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultListAppBar(onSearchClicked = {
                sharedViewModel.searchAppBarState.value = SearchAppBarState.OPENED
            }, onSortClicked = {}, onDeleteAllClicked = {})
        }

        else -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = { newText ->
                    sharedViewModel.searchTextState.value = newText
                },
                onCloseClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                    sharedViewModel.searchTextState.value = ""
                },
                onSearchClicked = {})
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppBar(
    onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit, onDeleteAllClicked: () -> Unit
) {
    TopAppBar(title = {
        Text(
            text = stringResource(id = R.string.app_bar_title),
            color = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }, colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.topAppBarBackgroundColor
    ), actions = {
        ListAppBarActions(
            onSearchClicked, onSortClicked, onDeleteAllClicked
        )
    })
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit, onSortClicked: (Priority) -> Unit, deleteAllAction: () -> Unit
) {
    SearchAction(onSearchClicked = onSearchClicked)
    SortAction(onSortClicked = onSortClicked)
    DeleteAllAction(onDeleteAllClicked = deleteAllAction)
}

@Composable
fun SearchAction(onSearchClicked: () -> Unit) {
    IconButton(onClick = { onSearchClicked() }) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(id = R.string.app_bar_content_description_search_tasks),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
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
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = { PriorityItem(priority = Priority.LOW) }, onClick = {
                expanded = false
                onSortClicked(Priority.LOW)
            })

            DropdownMenuItem(text = { PriorityItem(priority = Priority.MEDIUM) }, onClick = {
                expanded = false
                onSortClicked(Priority.MEDIUM)
            })

            DropdownMenuItem(text = { PriorityItem(priority = Priority.HIGH) }, onClick = {
                expanded = false
                onSortClicked(Priority.HIGH)
            })

            DropdownMenuItem(text = { PriorityItem(priority = Priority.NONE) }, onClick = {
                expanded = false
                onSortClicked(Priority.NONE)
            })
        }
    }
}

@Composable
fun DeleteAllAction(onDeleteAllClicked: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_menu),
            contentDescription = stringResource(id = R.string.app_bar_content_description_delete_all_tasks),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = {
                Text(
                    modifier = Modifier.padding(start = LARGE_PADDING),
                    text = stringResource(id = R.string.app_bar_content_description_delete_all_tasks),
                    style = Typography.titleSmall,
                )
            }, onClick = {
                expanded = false
                onDeleteAllClicked()
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    var trailingIconState by remember { mutableStateOf(TrailingIconState.READY_TO_CLOSE) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        tonalElevation = 0.dp,
        color = MaterialTheme.colorScheme.topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.app_bar_content_description_search_tasks),
                    color = MaterialTheme.colorScheme.topAppBarBackgroundColor
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.topAppBarContentColor,
                fontSize = MaterialTheme.typography.labelLarge.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(id = R.string.app_bar_content_description_search_tasks),
                        tint = MaterialTheme.colorScheme.topAppBarContentColor
                    )
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    when (trailingIconState) {
                        TrailingIconState.READY_TO_DELETE -> {
                            onTextChange("")
                            trailingIconState = TrailingIconState.READY_TO_CLOSE
                        }

                        TrailingIconState.READY_TO_CLOSE -> {
                            if (text.isNotEmpty()) {
                                onTextChange("")
                            } else {
                                onCloseClicked()
                                trailingIconState = TrailingIconState.READY_TO_DELETE
                            }
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(id = R.string.app_bar_content_description_search_tasks),
                        tint = MaterialTheme.colorScheme.topAppBarContentColor
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked(text) }),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colorScheme.topAppBarContentColor,
                focusedIndicatorColor = MaterialTheme.colorScheme.focusedContainerColor,
                disabledIndicatorColor = MaterialTheme.colorScheme.unfocusedContainerColor,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.unfocusedContainerColor,
                containerColor = MaterialTheme.colorScheme.focusedContainerColor
            )
        )
    }
}

@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultListAppBar(onSearchClicked = {}, onSortClicked = {}, onDeleteAllClicked = {})
}

@Composable
@Preview
private fun SearchAppBarPreview() {
    SearchAppBar(text = "Search", onTextChange = {}, onCloseClicked = {}, onSearchClicked = {})
}