package com.bcx.managersystem.entity;

import java.util.List;

/**
 * Created by 白杨 on 2016/5/16.
 */
public class Weather {

    private String msg;
    private String retCode;


    @Override
    public String toString() {
        return "Weather{" +
                "msg='" + msg + '\'' +
                ", retCode='" + retCode + '\'' +
                ", result=" + result +
                '}';
    }

    /**
     * airCondition : 良
     * city : 北京
     * coldIndex : 低发期
     * date : 2016-05-17
     * distrct : 北京
     * dressingIndex : 薄短袖类
     * exerciseIndex : 不适宜
     * future : [{"date":"2016-05-17","dayTime":"晴","night":"晴","temperature":"31°C / 17°C","week":"今天","wind":"无持续风向 小于3级"},{"date":"2016-05-18","dayTime":"晴","night":"多云","temperature":"28°C / 17°C","week":"星期三","wind":"无持续风向 小于3级"},{"date":"2016-05-19","dayTime":"多云","night":"晴","temperature":"27°C / 16°C","week":"星期四","wind":"无持续风向 小于3级"},{"date":"2016-05-20","dayTime":"晴","night":"多云","temperature":"29°C / 16°C","week":"星期五","wind":"无持续风向 小于3级"},{"date":"2016-05-21","dayTime":"多云","night":"阴","temperature":"28°C / 18°C","week":"星期六","wind":"无持续风向 小于3级"},{"date":"2016-05-22","dayTime":"阴","night":"阴","temperature":"25°C / 17°C","week":"星期日","wind":"无持续风向 小于3级"},{"date":"2016-05-23","dayTime":"阴","night":"多云","temperature":"25°C / 14°C","week":"星期一","wind":"无持续风向 小于3级"},{"date":"2016-05-24","dayTime":"少云","night":"晴","temperature":"27°C / 14°C","week":"星期二","wind":"西北风 3级"},{"date":"2016-05-25","dayTime":"晴","night":"少云","temperature":"27°C / 14°C","week":"星期三","wind":"西北风 4级"},{"date":"2016-05-26","dayTime":"少云","night":"少云","temperature":"29°C / 16°C","week":"星期四","wind":"西北偏西风 3级"}]
     * humidity : 湿度：46%
     * pollutionIndex : 96
     * province : 北京
     * sunrise : 04:58
     * sunset : 19:25
     * temperature : 21℃
     * time : 09:40
     * updateTime : 20160517095516
     * washIndex : 不太适宜
     * weather : 晴
     * week : 周二
     * wind : 无持续风向1级
     */

    private List<ResultEntity> result;

    public List<ResultEntity> getResult() {
        return result;
    }

    public void setResult(List<ResultEntity> result) {
        this.result = result;
    }

    public static class ResultEntity {
        @Override
        public String toString() {
            return "ResultEntity{" +
                    "airCondition='" + airCondition + '\'' +
                    ", city='" + city + '\'' +
                    ", coldIndex='" + coldIndex + '\'' +
                    ", updateTime='" + updateTime + '\'' +
                    ", date='" + date + '\'' +
                    ", distrct='" + distrct + '\'' +
                    ", dressingIndex='" + dressingIndex + '\'' +
                    ", exerciseIndex='" + exerciseIndex + '\'' +
                    ", humidity='" + humidity + '\'' +
                    ", province='" + province + '\'' +
                    ", sunset='" + sunset + '\'' +
                    ", sunrise='" + sunrise + '\'' +
                    ", temperature='" + temperature + '\'' +
                    ", time='" + time + '\'' +
                    ", washIndex='" + washIndex + '\'' +
                    ", weather='" + weather + '\'' +
                    ", week='" + week + '\'' +
                    ", wind='" + wind + '\'' +
                    ", pollutionIndex='" + pollutionIndex + '\'' +
                    ", future=" + future +
                    '}';
        }

        /**
         * 空气质量
         */
        private String airCondition;
        /**
         * 城市
         */
        private String city;
        /**
         * 感冒指数
         */
        private String coldIndex;
        /**
         * 更新时间
         */
        private String updateTime;
        /**
         * 日期
         */
        private String date;
        /**
         * 区县
         */
        private String distrct;
        /**
         * 穿衣指数
         */
        private String dressingIndex;
        /**
         * 运动指数
         */
        private String exerciseIndex;
        /**
         * 湿度
         */
        private String humidity;
        /**
         *省份
         */
        private String province;
        /**
         * 日落时间
         */
        private String sunset;
        /**
         * 日出时间
         */
        private String sunrise;
        /**
         * 温度
         */
        private String temperature;
        /**
         * 时间
         */
        private String time;
        /**
         * 洗车指数
         */
        private String washIndex;
        /**
         * 天气
         */
        private String weather;
        /**
         * 星期
         */
        private String week;
        /**
         * 风向
         */
        private String wind;
        private String pollutionIndex;
         /**
         * date : 2016-05-17
         * dayTime : 晴
         * night : 晴
         * temperature : 31°C / 17°C
         * week : 今天
         * wind : 无持续风向 小于3级
         */

        private List<FutureEntity> future;

        public String getAirCondition() {
            return airCondition;
        }

        public void setAirCondition(String airCondition) {
            this.airCondition = airCondition;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getColdIndex() {
            return coldIndex;
        }

        public void setColdIndex(String coldIndex) {
            this.coldIndex = coldIndex;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDistrct() {
            return distrct;
        }

        public void setDistrct(String distrct) {
            this.distrct = distrct;
        }

        public String getDressingIndex() {
            return dressingIndex;
        }

        public void setDressingIndex(String dressingIndex) {
            this.dressingIndex = dressingIndex;
        }

        public String getExerciseIndex() {
            return exerciseIndex;
        }

        public void setExerciseIndex(String exerciseIndex) {
            this.exerciseIndex = exerciseIndex;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPollutionIndex() {
            return pollutionIndex;
        }

        public void setPollutionIndex(String pollutionIndex) {
            this.pollutionIndex = pollutionIndex;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getWashIndex() {
            return washIndex;
        }

        public void setWashIndex(String washIndex) {
            this.washIndex = washIndex;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public List<FutureEntity> getFuture() {
            return future;
        }

        public void setFuture(List<FutureEntity> future) {
            this.future = future;
        }

        public static class FutureEntity {
            private String date;
            private String dayTime;
            private String night;
            private String temperature;
            private String week;
            private String wind;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getDayTime() {
                return dayTime;
            }

            public void setDayTime(String dayTime) {
                this.dayTime = dayTime;
            }

            public String getNight() {
                return night;
            }

            public void setNight(String night) {
                this.night = night;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            @Override
            public String toString() {
                return "FutureEntity{" +
                        "date='" + date + '\'' +
                        ", dayTime='" + dayTime + '\'' +
                        ", night='" + night + '\'' +
                        ", temperature='" + temperature + '\'' +
                        ", week='" + week + '\'' +
                        ", wind='" + wind + '\'' +
                        '}';
            }
        }
    }
}
