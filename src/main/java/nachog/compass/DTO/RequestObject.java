package nachog.compass.DTO;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestObject {

    @JsonProperty("userData")
    private UserData userData;

    @JsonProperty("surveyData")
    private Map<Long, Integer> surveyData;

    public RequestObject() {
        // Default constructor
    }

    public RequestObject(UserData userData, Map<Long, Integer> surveyData) {
        this.userData = userData;
        this.surveyData = surveyData;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Map<Long, Integer> getSurveyData() {
        return surveyData;
    }

    public void setSurveyData(Map<Long, Integer> surveyData) {
        this.surveyData = surveyData;
    }

    @Override
    public String toString() {
        return "RequestObject{" +
                "userData=" + userData +
                ", surveyData=" + surveyData +
                '}';
    }

    public static class UserData {
        private String name;
        private String email;
        private String birthday;
        private String gender;
        private String modality;

        public UserData() {
            // Default constructor
        }

        public UserData(String name, String email, String birthday, String gender, String modality) {
            this.name = name;
            this.email = email;
            this.birthday = birthday;
            this.gender = gender;
            this.modality = modality;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getModality() {
            return modality;
        }

        public void setModality(String modality) {
            this.modality = modality;
        }

        @Override
        public String toString() {
            return "UserData{" +
                    "name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", birthday='" + birthday + '\'' +
                    ", gender='" + gender + '\'' +
                    ", modality='" + modality + '\'' +
                    '}';
        }
    }
}
