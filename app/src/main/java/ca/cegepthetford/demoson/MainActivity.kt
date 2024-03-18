package ca.cegepthetford.demoson

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import ca.cegepthetford.demoson.ui.theme.DemoSonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoSonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //DemoMediaPlayerLocal()
                    DemoMediaPlayerInternet()
                }
            }
        }
    }
}

@Composable
fun DemoMediaPlayerLocal() {
    var mediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.echantillon_genesis)
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {
        Text("Version locale")
        Button(onClick = {
            mediaPlayer.start()
        } ) {
            Text("Ecouter")
        }
        Button(onClick = {
            //mediaPlayer.seekTo(0)
            mediaPlayer.pause()
        } ) {
            Text("Stop")
        }
    }
}
@Composable
fun DemoMediaPlayerInternet() {
    val uriPieceNo1 = "https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview126/v4/d3/78/78/d378782d-fc19-16c5-fbdd-f5892f143271/mzaf_12751219771906425547.plus.aac.p.m4a"
    val uriPieceNo2 = "https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview115/v4/e0/26/f8/e026f8aa-e2a6-64fa-8e43-8a886ac89b6e/mzaf_385604291212805166.plus.aac.p.m4a"

    val mediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
    }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {
        Text("Demo MediaPlayer")
        Button(onClick = {
            if(mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.reset()
            }
            mediaPlayer.setDataSource(uriPieceNo1)
            mediaPlayer.prepare()
        } ) {
            Text("Charger pièce #1")
        }
        Button(onClick = {
            if(mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.reset()
            }
            mediaPlayer.setDataSource(uriPieceNo2)
            mediaPlayer.prepare()
        } ) {
            Text("Charger pièce #2")
        }
        Button(onClick = {
            if(!mediaPlayer.isPlaying)
                mediaPlayer.start()
        } ) {
            Text("Jouer")
        }
        Button(onClick = {
            mediaPlayer.pause()
        } ) {
            Text("Pause")
        }
        Button(onClick = {
            if(mediaPlayer.isPlaying) {
                mediaPlayer.stop()
                mediaPlayer.prepare()
            }
        } ) {
            Text("Arrêter")
        }
    }
}
