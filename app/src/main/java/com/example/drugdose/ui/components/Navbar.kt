package com.example.drugdose.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.xr.compose.material3.ExperimentalMaterial3XrApi
import androidx.xr.compose.material3.HorizontalFloatingToolbar
import com.example.drugdose.R

@OptIn(
    ExperimentalMaterial3XrApi::class,
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3ExpressiveApi::class
)
@Composable
fun NavBar(
    onProfiles: () -> Unit,
    onInfo: () -> Unit,
    onMeds: () -> Unit,
) {
    HorizontalFloatingToolbar(
        expanded = true,
        shape = RoundedCornerShape(25),
        contentPadding = PaddingValues(8.dp),
        content = {

                IconButton(modifier = Modifier.padding(8.dp),
                        onClick = onMeds)  {
                    Icon(
                        painterResource(id = R.drawable.admin_meds_40px),
                        contentDescription = "Medicine List"
                    )
                }
                IconButton(modifier = Modifier.padding(8.dp),onClick = onInfo) {
                    Icon(
                        painterResource(id = R.drawable.info_40px),
                        contentDescription = "App Information"
                    )
                }
              IconButton(modifier = Modifier.padding(8.dp),onClick = onProfiles) {
                    Icon(
                        painterResource(id = R.drawable.person_40px),
                        contentDescription = "Account List"
                    )
                }

        }
    )
}
