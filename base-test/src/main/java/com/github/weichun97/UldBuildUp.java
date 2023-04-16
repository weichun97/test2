package com.github.weichun97;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UldBuildUp {

    public static void main(String[] args) {
        List<ULD> uldList = new ArrayList<>(); // ULD列表

        // 初始化货物列表
        List<RectangularBox> boxes = new ArrayList<>();
        boxes.add(new RectangularBox(30, 40, 50));
        boxes.add(new RectangularBox(20, 30, 40));
        boxes.add(new RectangularBox(10, 20, 30));
        boxes.add(new RectangularBox(40, 50, 60));
        boxes.add(new RectangularBox(30, 30, 30));

        // 尝试将货物放入ULD中
        for (RectangularBox box : boxes) {
            boolean added = false;
            for (ULD uld : uldList) {
                if ((box.width <= uld.width && box.height <= uld.height && box.depth <= uld.depth)
                        || (box.height <= uld.width && box.width <= uld.height && box.depth <= uld.depth)
                        || (box.depth <= uld.width && box.height <= uld.height && box.width <= uld.depth)) {
                    uld.addBox(box);
                    added = true;
                    break;
                } else if ((box.width <= uld.height && box.height <= uld.width && box.depth <= uld.depth)
                        || (box.height <= uld.height && box.width <= uld.width && box.depth <= uld.depth)
                        || (box.depth <= uld.height && box.height <= uld.width && box.width <= uld.depth)) {
                    uld.rotate();
                    uld.addBox(box);
                    added = true;
                    break;
                } else if ((box.width <= uld.depth && box.height <= uld.width && box.depth <= uld.height)
                        || (box.height <= uld.depth && box.width <= uld.width && box.depth <= uld.height)
                        || (box.depth <= uld.depth && box.height <= uld.width && box.width <= uld.height)) {
                    uld.rotate();
                    uld.rotate();
                    uld.addBox(box);
                    added = true;
                    break;
                }
            }

            // 如果无法放入已有的ULD中，则创建新的ULD
            if (!added) {
                ULD newUld = new ULD(100, 100, 100); // 假设新的ULD尺寸为100x100x100
                newUld.addBox(box);
                uldList.add(newUld);
            }
        }

        // 输出装箱结果
        System.out.println("ULD装箱结果：");
        for (int i = 0; i < uldList.size(); i++) {
            ULD uld = uldList.get(i);
            System.out.println("ULD " + (i + 1) + ":");
            for (RectangularBox box : uld.boxes) {
                System.out.println("  - Box: " + box.width + "x" + box.height + "x" + box.depth);
            }
        }
    }

    static class RectangularBox {
        int width;
        int height;
        int depth;

        public RectangularBox(int width, int height, int depth) {
            this.width = width;
            this.height = height;
            this.depth = depth;
        }
    }

    static class ULD {
        int width;
        int height;
        int depth;
        List<RectangularBox> boxes;

        public ULD(int width, int height, int depth) {
            this.width = width;
            this.height = height;
            this.depth = depth;
            boxes = new ArrayList<>();
        }

        public void addBox(RectangularBox box) {
            boxes.add(box);
        }

        public void rotate() {
            int temp = width;
            width = height;
            height = temp;
        }
    }

    static class BoxComparator implements Comparator<RectangularBox> {
        @Override
        public int compare(RectangularBox b1, RectangularBox b2) {
            return Integer.compare(Math.max(b2.width, b2.height), Math.max(b1.width, b1.height));
        }
    }
}


