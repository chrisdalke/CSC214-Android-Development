////////////////////////////////////////
// Chris Dalke
// cdalke@u.rochester.edu
// CSC 214
// TA: Mariana Kim
////////////////////////////////////////
// Project 3
////////////////////////////////////////

package csc214.project03.Model.Achievements;

////////////////////////////////////////
// Achievement Model
////////////////////////////////////////

public class Achievement {
    private String name;
    private String description;
    private boolean completed;
    private AchievementConditionCheck conditionCheck;

    public Achievement(String name, String description, AchievementConditionCheck conditionCheck) {
        this.name = name;
        this.description = description;
        this.conditionCheck = conditionCheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public AchievementConditionCheck getConditionCheck() {
        return conditionCheck;
    }

    public void setConditionCheck(AchievementConditionCheck conditionCheck) {
        this.conditionCheck = conditionCheck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Achievement that = (Achievement) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}

////////////////////////////////////////
// End of Module
////////////////////////////////////////