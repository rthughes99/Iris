package com.volmit.iris.util.uniques.features;

import com.volmit.iris.util.math.RNG;
import com.volmit.iris.util.noise.CNG;
import com.volmit.iris.util.uniques.UFeature;
import com.volmit.iris.util.uniques.UFeatureMeta;
import com.volmit.iris.util.uniques.UImage;

import java.awt.*;
import java.util.function.Consumer;

public class UFWarpedBackground implements UFeature {
    @Override
    public void render(UImage image, RNG rng, double t, Consumer<Double> progressor, UFeatureMeta meta) {
        CNG hue = generator("color_hue", rng, rng.d(0.001, rng.d(2, 5)), rng.i(0, 3) ,rng.i(0, 3), 31007, meta);
        CNG sat = generator("color_sat", rng, rng.d(0.001, rng.d(2, 5)), rng.i(0, 2) ,rng.i(0, 2), 33004, meta);
        CNG bri = generator("color_bri", rng, rng.d(0.001, rng.d(2, 5)), rng.i(0, 1) ,rng.i(0, 1), 32005, meta).patch(0.145);
        double tcf = rng.d(0.15, 0.55);

        for(int i = 0; i < image.getWidth(); i++)
        {
            for(int j = 0; j < image.getHeight(); j++)
            {
                image.set(i, j, color(hue, sat, bri, i, j, tcf * t));
            }

            progressor.accept(i / (double)image.getWidth());
        }
    }
}
