package fi.metropolia.largear

import android.graphics.Point
import android.os.Bundle
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.animation.ModelAnimator
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.google.ar.sceneform.rendering.ModelRenderable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val HEIST_MODEL = "heist.sfb"
        const val TANK_MODEL = "tank.sfb"
    }

    private var myRenderable: ModelRenderable? = null
    lateinit var modelAnimator: ModelAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = arFragment as ArFragment

        ModelRenderable.builder()
            .setSource(fragment.context, Uri.parse(HEIST_MODEL))
            .build()
            .thenAccept{
                myRenderable = it
                modelAnimator = ModelAnimator(
                    myRenderable!!.getAnimationData(0),
                    myRenderable
                )
                modelAnimator.repeatCount = ModelAnimator.INFINITE
                modelAnimator.start()
            }

        fragment.setOnTapArPlaneListener { hitResult, _, _ ->
            if (myRenderable == null) return@setOnTapArPlaneListener
            val anchor = hitResult!!.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(fragment.arSceneView.scene)
            val viewNode = TransformableNode(fragment.transformationSystem)

            viewNode.scaleController.minScale = 2.6f
            viewNode.scaleController.maxScale = 2.7f
            viewNode.rotationController.rotationRateDegrees = 180f

            viewNode.setParent(anchorNode)
            viewNode.renderable = myRenderable
            viewNode.select()
        }
    }

//    private fun getScreenCenter(): Point {
//        val v = arFragment.view!!
//        return Point(v.width / 2, v.height / 2)
//    }

}
