package appbattery.cobalt.com.batteryapp.animations;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;
import java.util.List;

public class WaveViewHelper {

	private WaveView managedWaveView;
	private AnimatorSet animatorSet;
	private int batteryPercentage = 0;

	public WaveViewHelper(final WaveView waveView) {
		managedWaveView = waveView;
		initAnimation();
	}

	public void setBatteryPercentage(final int percentage) {
		if (percentage >= 0 && percentage <= 100) {
			batteryPercentage = percentage;
		}

		// restart the animation
		cancel();
		initAnimation();
		start();
	}

	public void start() {
		managedWaveView.setShowWave(true);
		if (animatorSet != null) {
			animatorSet.start();
		}
	}

	private void initAnimation() {
		List<Animator> animators = new ArrayList<>();

		// horizontal animation.
		// wave waves infinitely.
		ObjectAnimator waveShiftAnim = ObjectAnimator.ofFloat(managedWaveView, "waveShiftRatio", 0f, 1f);
		waveShiftAnim.setRepeatCount(ValueAnimator.INFINITE);
		waveShiftAnim.setDuration(1000);
		waveShiftAnim.setInterpolator(new LinearInterpolator());
		animators.add(waveShiftAnim);

		// vertical animation.
		// water level increases from 0 to center of WaveView
		ObjectAnimator waterLevelAnim = ObjectAnimator.ofFloat(managedWaveView, "waterLevelRatio", 0f, batteryPercentage / 100.0f);
		waterLevelAnim.setDuration(10000);
		waterLevelAnim.setInterpolator(new DecelerateInterpolator());
		animators.add(waterLevelAnim);

		// amplitude animation.
		// wave grows big then grows small, repeatedly
		ObjectAnimator amplitudeAnim = ObjectAnimator.ofFloat(managedWaveView, "amplitudeRatio", 0.0001f, 0.05f);
		amplitudeAnim.setRepeatCount(ValueAnimator.INFINITE);
		amplitudeAnim.setRepeatMode(ValueAnimator.REVERSE);
		amplitudeAnim.setDuration(5000);
		amplitudeAnim.setInterpolator(new LinearInterpolator());
		animators.add(amplitudeAnim);

		animatorSet = new AnimatorSet();
		animatorSet.playTogether(animators);
	}

	public void cancel() {
		if (animatorSet != null) {
			animatorSet.end();
		}
	}
}