package fi.metropolia.arcore

import android.os.Bundle
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.google.ar.sceneform.rendering.ModelRenderable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val MODEL_NAME = "Buu_FinalMesh.sfb"
        const val TAILS_MODEL = "CH_UgandaTails_Sketchfab.sfb"
        const val BOOK_MODEL = "Book_scan.sfb"
    }

    private var myRenderable: ModelRenderable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = arFragment as ArFragment

        ModelRenderable.builder()
            .setSource(fragment.context, Uri.parse(BOOK_MODEL))
            .build()
            .thenAccept{ myRenderable = it }

        fragment.setOnTapArPlaneListener { hitResult, _, _ ->
            if (myRenderable == null) return@setOnTapArPlaneListener
            val anchor = hitResult!!.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(fragment.arSceneView.scene)
            val viewNode = TransformableNode(fragment.transformationSystem)
            viewNode.setParent(anchorNode)
            viewNode.renderable = myRenderable
            viewNode.select()
        }
    }
}
