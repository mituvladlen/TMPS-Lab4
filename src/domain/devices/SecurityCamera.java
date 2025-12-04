package domain.devices;

/**
 * Concrete Smart Security Camera device
 */
public class SecurityCamera extends SmartDevice {
    private boolean isRecording;
    private String recordingMode; // "motion-detection" or "continuous"

    public SecurityCamera(String deviceId, String name) {
        super(deviceId, name);
        this.isRecording = false;
        this.recordingMode = "motion-detection";
    }

    public void startRecording() {
        if (!isRecording) {
            isRecording = true;
            notifyObservers("Recording started in " + recordingMode + " mode");
        }
    }

    public void stopRecording() {
        if (isRecording) {
            isRecording = false;
            notifyObservers("Recording stopped");
        }
    }

    public void setRecordingMode(String mode) {
        this.recordingMode = mode;
        notifyObservers("Recording mode set to " + mode);
    }

    public boolean isRecording() {
        return isRecording;
    }

    @Override
    public String getStatus() {
        return String.format("Camera '%s' [%s] - Power: %s, Recording: %s, Mode: %s",
                name, deviceId, isOn ? "ON" : "OFF",
                isRecording ? "YES" : "NO", recordingMode);
    }
}

