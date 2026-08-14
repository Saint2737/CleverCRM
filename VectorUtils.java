package util;

import java.util.List;

public final class VectorUtils {

	private VectorUtils() {
	}

	public static float[] toFloatArray(List<? extends Number> values) {
		float[] vector = new float[values.size()];
		for (int i = 0; i < values.size(); i++) {
			vector[i] = values.get(i).floatValue();
		}
		return vector;
	}
}
