package com.app.websocket.model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "detection_logs")
public class DetectionLog {
    @Id
    private String id;
    private String serviceId;
    private Instant timestamp;
    private String prediction;
    private double confidence;
    private boolean isAnomaly;
    private Object rawFeatures;
    private Object explanation;

    public DetectionLog() {
    }

    public DetectionLog(String serviceId, Instant timestamp, String prediction, double confidence, boolean isAnomaly, Object rawFeatures, Object explanation) {
        this.serviceId = serviceId;
        this.timestamp = timestamp;
        this.prediction = prediction;
        this.confidence = confidence;
        this.isAnomaly = isAnomaly;
        this.rawFeatures = rawFeatures;
        this.explanation = explanation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public boolean isAnomaly() {
        return isAnomaly;
    }

    public void setAnomaly(boolean isAnomaly) {
        this.isAnomaly = isAnomaly;
    }

    public Object getRawFeatures() {
        return rawFeatures;
    }

    public void setRawFeatures(Object rawFeatures) {
        this.rawFeatures = rawFeatures;
    }

    public Object getExplanation() {
        return explanation;
    }

    public void setExplanation(Object explanation) {
        this.explanation = explanation;
    }
}
