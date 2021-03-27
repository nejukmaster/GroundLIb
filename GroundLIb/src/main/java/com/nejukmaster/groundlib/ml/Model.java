package com.nejukmaster.groundlib.ml;

import java.io.File;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.function.Supplier;

import org.tensorflow.DataType;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;

import com.nejukmaster.groundlib.main;
import com.nejukmaster.groundlib.utils;
import com.nejukmaster.groundlib.exceptions.MPP_DOESNT_VALIDATE_EXCEPTION;
import com.nejukmaster.groundlib.exceptions.NO_MPP_EXIST_EXCEPTION;

public class Model {
	
	String path;
	String input_name;
	String output_name;
	int output_nodes;
	
	public Model(String name) throws NO_MPP_EXIST_EXCEPTION,MPP_DOESNT_VALIDATE_EXCEPTION{
		path = main.model_dir+"/"+name;
		File folder = new File(path);
		if(!new File(path+"/model_props.mpp").exists()) {
			System.out.println("Can't find models_props.mpp in "+folder.getName());
			throw new NO_MPP_EXIST_EXCEPTION();
		}
		else {
			for(File f : folder.listFiles()) {
				if(f.getName().equalsIgnoreCase("model_props.mpp")) {
					HashMap<String,String> m = utils.loadConfig(f);
					if(!m.containsKey("input") || !m.containsKey("output") || !m.containsKey("output_nodes"))
						throw new MPP_DOESNT_VALIDATE_EXCEPTION();
					else
						this.input_name = m.get("input");
						this.output_name = m.get("output");
						this.output_nodes = Integer.parseInt(m.get("output_nodes"));
				}
			}
		}
	}
	
	public Supplier<? extends Buffer> predict(float[] in){
		try (SavedModelBundle model = SavedModelBundle.load(path, "serve")) {
			try (Tensor<Float> input = (Tensor<Float>) Tensor.create(in);
					Tensor<?> output = model.session().runner().feed(input_name, input).fetch(output_name).run().get(0)) {
					Supplier<? extends Buffer> sup = ()->{
						switch(output.dataType()) {
							case INT32:
								IntBuffer buf1 = IntBuffer.allocate(output_nodes);
								output.writeTo(buf1);
								return buf1;
							case INT64:
								IntBuffer buf2 = IntBuffer.allocate(output_nodes);
								output.writeTo(buf2);
								return buf2;
							case UINT8:
								IntBuffer buf3 = IntBuffer.allocate(output_nodes);
								output.writeTo(buf3);
								return buf3;
							case FLOAT:
								FloatBuffer buf4 = FloatBuffer.allocate(output_nodes);
								output.writeTo(buf4);
								return buf4;
							default:
								return null;
						}
					};
					return sup;
				  }
		}
	}
	
	public Supplier<? extends Buffer> predict(float[][] in){
		try (SavedModelBundle model = SavedModelBundle.load(path, "serve")) {
			try (Tensor<Float> input = (Tensor<Float>) Tensor.create(in);
					Tensor<?> output = model.session().runner().feed(input_name, input).fetch(output_name).run().get(0)) {
				FloatBuffer fb = null;
				IntBuffer ib = null;
				switch(output.dataType()) {
					case INT32:
						ib = IntBuffer.allocate(output_nodes);
						output.writeTo(ib);
						break;
					case INT64:
						ib = IntBuffer.allocate(output_nodes);
						output.writeTo(ib);
						break;
					case UINT8:
						ib = IntBuffer.allocate(output_nodes);
						output.writeTo(ib);
						break;
					case FLOAT:
						fb = FloatBuffer.allocate(output_nodes);
						output.writeTo(fb);
						break;
				}
				final FloatBuffer _fb = fb;
				final IntBuffer _ib = ib;
				Supplier<? extends Buffer> sup = ()->{
						if(_ib == null) {
							if(_fb == null) {
								return null;
							}
							return _fb;
						}
						else
							return _ib;
					};
				return sup;
				}
		}
	}
	
	public Supplier<? extends Buffer> predict(float[][][] in){
		try (SavedModelBundle model = SavedModelBundle.load(path, "serve")) {
			try (Tensor<Float> input = (Tensor<Float>) Tensor.create(in);
					Tensor<?> output = model.session().runner().feed(input_name, input).fetch(output_name).run().get(0)) {
					Supplier<? extends Buffer> sup = ()->{
						switch(output.dataType()) {
							case INT32:
								IntBuffer buf1 = IntBuffer.allocate(output_nodes);
								output.writeTo(buf1);
								return buf1;
							case INT64:
								IntBuffer buf2 = IntBuffer.allocate(output_nodes);
								output.writeTo(buf2);
								return buf2;
							case UINT8:
								IntBuffer buf3 = IntBuffer.allocate(output_nodes);
								output.writeTo(buf3);
								return buf3;
							case FLOAT:
								FloatBuffer buf4 = FloatBuffer.allocate(output_nodes);
								output.writeTo(buf4);
								return buf4;
							default:
								return null;
						}
					};
					return sup;
				  }
		}
	}
	
	public Supplier<? extends Buffer> predict(float[][][][] in){
		try (SavedModelBundle model = SavedModelBundle.load(path, "serve")) {
			try (Tensor<Float> input = (Tensor<Float>) Tensor.create(in);
					Tensor<?> output = model.session().runner().feed(input_name, input).fetch(output_name).run().get(0)) {
				FloatBuffer fb = null;
				IntBuffer ib = null;
				switch(output.dataType()) {
					case INT32:
						ib = IntBuffer.allocate(output_nodes);
						output.writeTo(ib);
						break;
					case INT64:
						ib = IntBuffer.allocate(output_nodes);
						output.writeTo(ib);
						break;
					case UINT8:
						ib = IntBuffer.allocate(output_nodes);
						output.writeTo(ib);
						break;
					case FLOAT:
						fb = FloatBuffer.allocate(output_nodes);
						output.writeTo(fb);
						break;
				}
				final FloatBuffer _fb = fb;
				final IntBuffer _ib = ib;
				Supplier<? extends Buffer> sup = ()->{
						if(_ib == null) {
							if(_fb == null) {
								return null;
							}
							return _fb;
						}
						else
							return _ib;
					};
				return sup;
				}
		}
	}
	
	public Supplier<? extends Buffer> predict(float[][][][][] in){
		try (SavedModelBundle model = SavedModelBundle.load(path, "serve")) {
			try (Tensor<Float> input = (Tensor<Float>) Tensor.create(in);
					Tensor<?> output = model.session().runner().feed(input_name, input).fetch(output_name).run().get(0)) {
				FloatBuffer fb = null;
				IntBuffer ib = null;
				switch(output.dataType()) {
					case INT32:
						ib = IntBuffer.allocate(output_nodes);
						output.writeTo(ib);
						break;
					case INT64:
						ib = IntBuffer.allocate(output_nodes);
						output.writeTo(ib);
						break;
					case UINT8:
						ib = IntBuffer.allocate(output_nodes);
						output.writeTo(ib);
						break;
					case FLOAT:
						fb = FloatBuffer.allocate(output_nodes);
						output.writeTo(fb);
						break;
				}
				final FloatBuffer _fb = fb;
				final IntBuffer _ib = ib;
				Supplier<? extends Buffer> sup = ()->{
						if(_ib == null) {
							if(_fb == null) {
								return null;
							}
							return _fb;
						}
						else
							return _ib;
					};
				return sup;
				}
		}
	}

}
