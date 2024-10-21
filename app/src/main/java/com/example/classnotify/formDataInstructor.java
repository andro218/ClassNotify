package com.example.classnotify;

public class formDataInstructor {
    private String subjectName;
    private String subjectCode;
    private String blockCourse;
    private String instructor;
    private String room;
    private String fromTimeSpinnerSelection;
    private String toTimeSpinnerSelection;
    private boolean monCheckbox;
    private boolean tueCheckbox;
    private boolean wedCheckbox;
    private boolean thuCheckbox;
    private boolean friCheckbox;
    private boolean satCheckbox;
    private boolean sunCheckbox;

    // Constructor
    public formDataInstructor(String subjectName, String subjectCode, String blockCourse, String instructor, String room,
                              String fromTimeSpinnerSelection, String toTimeSpinnerSelection, boolean monCheckbox,
                              boolean tueCheckbox, boolean wedCheckbox, boolean thuCheckbox, boolean friCheckbox,
                              boolean satCheckbox, boolean sunCheckbox) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.blockCourse = blockCourse;
        this.instructor = instructor;
        this.room = room;
        this.fromTimeSpinnerSelection = fromTimeSpinnerSelection;
        this.toTimeSpinnerSelection = toTimeSpinnerSelection;
        this.monCheckbox = monCheckbox;
        this.tueCheckbox = tueCheckbox;
        this.wedCheckbox = wedCheckbox;
        this.thuCheckbox = thuCheckbox;
        this.friCheckbox = friCheckbox;
        this.satCheckbox = satCheckbox;
        this.sunCheckbox = sunCheckbox;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getBlockCourse() {
        return blockCourse;
    }

    public void setBlockCourse(String blockCourse) {
        this.blockCourse = blockCourse;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getFromTimeSpinnerSelection() {
        return fromTimeSpinnerSelection;
    }

    public void setFromTimeSpinnerSelection(String fromTimeSpinnerSelection) {
        this.fromTimeSpinnerSelection = fromTimeSpinnerSelection;
    }

    public String getToTimeSpinnerSelection() {
        return toTimeSpinnerSelection;
    }

    public void setToTimeSpinnerSelection(String toTimeSpinnerSelection) {
        this.toTimeSpinnerSelection = toTimeSpinnerSelection;
    }

    public boolean isMonCheckbox() {
        return monCheckbox;
    }

    public void setMonCheckbox(boolean monCheckbox) {
        this.monCheckbox = monCheckbox;
    }

    public boolean isTueCheckbox() {
        return tueCheckbox;
    }

    public void setTueCheckbox(boolean tueCheckbox) {
        this.tueCheckbox = tueCheckbox;
    }

    public boolean isWedCheckbox() {
        return wedCheckbox;
    }

    public void setWedCheckbox(boolean wedCheckbox) {
        this.wedCheckbox = wedCheckbox;
    }

    public boolean isThuCheckbox() {
        return thuCheckbox;
    }

    public void setThuCheckbox(boolean thuCheckbox) {
        this.thuCheckbox = thuCheckbox;
    }

    public boolean isFriCheckbox() {
        return friCheckbox;
    }

    public void setFriCheckbox(boolean friCheckbox) {
        this.friCheckbox = friCheckbox;
    }

    public boolean isSatCheckbox() {
        return satCheckbox;
    }

    public void setSatCheckbox(boolean satCheckbox) {
        this.satCheckbox = satCheckbox;
    }

    public boolean isSunCheckbox() {
        return sunCheckbox;
    }

    public void setSunCheckbox(boolean sunCheckbox) {
        this.sunCheckbox = sunCheckbox;
    }
}
