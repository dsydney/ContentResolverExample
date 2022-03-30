package com.revature.contentresolverexample

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.revature.contentresolverexample.ui.theme.ContentResolverExampleTheme
import java.lang.StringBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentResolverExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CR_UI()
                }
            }
        }
    }
}

const val URL = "content://com.revature.contentproviderexample.RevatureCP.provider/users"
val CONTENT_URI: Uri = Uri.parse(URL)

@SuppressLint("Range")
@Composable
fun CR_UI() {

    val context = LocalContext.current

    Column(
        Modifier.fillMaxHeight(0.5f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        var result  by remember{ mutableStateOf("") }

        Button(onClick = {

            val cursor = context.contentResolver.query(CONTENT_URI, null, null, null, null)

            if(cursor!!.moveToFirst()) {

                val strBuild = StringBuilder()
                while(!cursor.isAfterLast) {
                    result += "${cursor.getString(cursor.getColumnIndex("id"))}-${cursor.getString(cursor.getColumnIndex("name"))}\n"
                    cursor.moveToNext()
                }

                Log.d("Data", "$result")

            } else {

                Log.d("Data", "No Records found")

            }

        }) {


            Text(text = "Show Records")
        }

    }

}