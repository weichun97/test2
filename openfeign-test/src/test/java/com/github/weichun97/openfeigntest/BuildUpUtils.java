package com.github.weichun97.openfeigntest;

import java.util.List;

/**
 * 打板工具类
 *
 * @author chun
 * @date 2022/4/25 19:53
 */
public class BuildUpUtils {

    /**
     * 根据货物信息和容器信息计算出每个容器的货物放置位置，
     * 使容器利用率最大化
     * @param goods 货物信息
     * @param containers 容器信息
     * @return 容器信息
     */
    public static List<Container> calculate(List<Goods> goods, List<Container> containers){
        return null;
    }

    /**
     * 货物
     */
    public static class Goods {

        /**
         * 长(CM)
         */
        private Integer length;

        /**
         * 宽(CM)
         */
        private Integer width;

        /**
         * 高(CM)
         */
        private Integer height;

        /**
         * 重量(KG)
         */
        private Double weight;

        /**
         * 轮廓
         * 1:长方体
         * 2:三角柱
         */
        private Integer contour;

        /**
         * 个数
         */
        private Integer size;

        /**
         * 打板结果
         */
        private List<BuildUpResult> BuildUpResult;

        public Integer getLength() {
            return length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public Double getWeight() {
            return weight;
        }

        public void setWeight(Double weight) {
            this.weight = weight;
        }

        public Integer getContour() {
            return contour;
        }

        public void setContour(Integer contour) {
            this.contour = contour;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public List<BuildUpUtils.BuildUpResult> getBuildUpResult() {
            return BuildUpResult;
        }

        public void setBuildUpResult(List<BuildUpUtils.BuildUpResult> buildUpResult) {
            BuildUpResult = buildUpResult;
        }
    }

    /**
     * 容器
     */
    public static class Container {

        /**
         * <pre>
         * 类型名称
         * 集装器为：类型+轮廓，例如：PMC Q6
         * 集装箱为：类型，例如：AKE
         * </pre>
         */
        private String typeName;

        /**
         * 长(CM)
         */
        private Integer length;

        /**
         * 宽(CM)
         */
        private Integer width;

        /**
         * 高(CM)
         */
        private Integer height;

        /**
         * 承重量(KG)
         */
        private Integer bearing;

        /**
         * 个数
         */
        private Integer size;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public Integer getLength() {
            return length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public Integer getBearing() {
            return bearing;
        }

        public void setBearing(Integer bearing) {
            this.bearing = bearing;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }
    }

    /**
     * 打板结果
     */
    public static class BuildUpResult {

        /**
         * 长(CM)
         */
        private Integer length;

        /**
         * 宽(CM)
         */
        private Integer width;

        /**
         * 高(CM)
         */
        private Integer height;

        /**
         * 俯视图左上角的点 x 轴坐标(CM)
         */
        private Integer x;

        /**
         * 俯视图左上角的点 y 轴坐标(CM)
         */
        private Integer y;

        /**
         * 俯视图左上角的点 z 轴坐标(CM)
         */
        private Integer z;

        /**
         * 层数（以最高层为准）
         */
        private Integer layer;

        public Integer getLength() {
            return length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }

        public Integer getZ() {
            return z;
        }

        public void setZ(Integer z) {
            this.z = z;
        }

        public Integer getLayer() {
            return layer;
        }

        public void setLayer(Integer layer) {
            this.layer = layer;
        }
    }

    public static void main(String[] args) {
        int i;
        for (i = 2; i <= 10; i++){
//            System.out.println(i);
        }
        System.out.println(i);
    }
}
