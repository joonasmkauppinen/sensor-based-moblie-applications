package fi.metropolia.arcore

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
        const val STORMTROOPER_MODEL = "silly_dancing.sfb"
        const val MINECRAFT_CHEST_MODEL = "scene.sfb"
        const val UGANDA_TAILS_MODEL = "UgandaTails.sfb"
        const val THANOS_MODEL = "Thanos.sfb"
    }

    private var myRenderable: ModelRenderable? = null
    lateinit var modelAnimator: ModelAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = arFragment as ArFragment

        ModelRenderable.builder()
            .setSource(fragment.context, Uri.parse(THANOS_MODEL))
            .build()
            .thenAccept{
                myRenderable = it
                modelAnimator = ModelAnimator(
                    myRenderable!!.getAnimationData(0),
                    myRenderable)
                modelAnimator.repeatCount = ModelAnimator.INFINITE
                modelAnimator.start()
            }


        fragment.setOnTapArPlaneListener { hitResult, _, _ ->
            if (myRenderable == null) return@setOnTapArPlaneListener
            val anchor = hitResult!!.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(fragment.arSceneView.scene)
            val viewNode = TransformableNode(fragment.transformationSystem)
            viewNode.setParent(anchorNode)
            viewNode.renderable = myRenderable
            viewNode.select()

//            val point = getScreenCenter()
//            val hits: List<HitResult> = fragment.arSceneView.arFrame!!
//                .hitTest(point.x.toFloat(), point.y.toFloat())
//            for (hit in hits) {
//                val trackable = hit.trackable
//                if (trackable is Plane) {
//
////                    ModelAnimator(myRenderable?.getAnimationData(0), myRenderable)
////                        .start()
//                }
//            }

        }
    }

    private fun getScreenCenter(): Point {
        val v = arFragment.view!!
        return Point(v.width / 2, v.height / 2)
    }
}
