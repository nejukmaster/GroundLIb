package com.nejukmaster.groundlib.ml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.Supplier;

import org.tensorflow.Graph;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;

public class ML {
	
	public static String[] FashionMNistClasses = new String[] {"Æ¼¼ÅÃ÷","¹ÙÁö","½º¿þÅÍ","µå·¹½º","ÄÚÆ®","»÷´Þ","¼ÅÃ÷","¿îµ¿È­","°¡¹æ","±¸µÎ"};
	
	public static float[] floatPredict(String path, float[][] f, int output_neurun) {
		try (SavedModelBundle model = SavedModelBundle.load(path, "serve")) {
			try (Tensor<Float> input = (Tensor<Float>) Tensor.create(f);
					Tensor<?> output = model.session().runner().feed("serving_default_flatten_7_input", input).fetch("StatefulPartitionedCall").run().get(0)) {
					FloatBuffer fb = FloatBuffer.allocate(10);
					output.writeTo(fb);
					return fb.array();
				  }
		}
    }
	
	public static Tensor<?> floatPredict(SavedModelBundle model_bundle, float[] f, int output_neurun) {
		try (Tensor<Float> input = (Tensor<Float>) Tensor.create(f);
			       Tensor<?> output = model_bundle.session().runner().feed("serving_default_flatten_7_input", input).fetch("StatefulPartitionedCall").run().get(0)) {
			  return output;
			  }
	}
}
