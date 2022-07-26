package samsung.swtest_july;

import java.util.*;
import java.util.stream.Collectors;

public class UserSolution {

    private static final int GRADE = 3;
    private static final int GENDER = 2;
    ArrayList[][] students;

    static class Student implements Comparable<Student> {
        int mId;
        int mGrade;
        char[] mGender;
        int mScore;

        Student(int mId, int mGrade, char[] mGender, int mScore) {
            this.mId = mId;
            this.mGrade = mGrade;
            this.mGender = mGender;
            this.mScore = mScore;
        }

        /* 1차적으로 점수 순, 2차적으로 mId 순 */
        /*public int compareTo(Student o) {
            if(this.mScore > o.mScore){
                if(this.mId > o.mId){
                    return 4; // 최상위 우선순위
                }else{
                    return 3; // 상위 우선순위
                }
            }else if(this.mScore < o.mScore){
                if(this.mId < o.mId){
                    return -2; // 최하위 우선순위
                }else{
                    return -1;
                }
            }else{
                if(this.mId > o.mId){
                    return 2;
                }else if(this.mId < o.mId){
                    return 1;
                }else{
                    return 0;
                }
            }
        }*/

        @Override
        public int compareTo(Student o) {
            /*if (this.mScore + this.mId > o.mScore + o.mId) {// mId는 각각 다르기 때문에, 구분된다고 고려하였다.
                return 1;
            } else if (this.mScore + this.mId < o.mScore + o.mId) {
                return -1;
            }
            return 0;*/
            if (this.mScore > o.mScore) {
                return 1;
            } else if (this.mScore < o.mScore) {
                return -1;
            } else {
                return Integer.compare(this.mId, o.mId);
            }
        }
    }

    public void init() {
        students = new ArrayList[GRADE][GENDER];
        /* 0 : 1 학년, 1 : 2학년, 2 : 3학년 */
        for (int i = 0; i < GRADE; i++) {
            /* 0 : male, 1 : female*/
            for (int j = 0; j < GENDER; j++) {
                students[i][j] = new ArrayList<Student>();
            }
        }

    }

    /**
     * mGrade학년 mGender인 학생 중에서 점수가 가장 높은 학생의 ID를 반환
     * - 점수가 가장 높은 학생이 여러 명이라면, 그 중에서 ID가 가장 큰 값을 반환
     * - 시스템에 이미 등록된 mId를 갖는 학생은 추가되지 않음. 삭제되지 않는 한.
     *
     * @param mId
     * @param mGrade
     * @param mGender
     * @param mScore
     * @return
     */
    public int add(int mId, int mGrade, char mGender[], int mScore) {
        Student student = new Student(mId, mGrade, mGender, mScore);
        Student mostHighStudent;
        ArrayList<Student> temp;

        /* DB에 삽입 후 해당 학년, 성별에서 가장 점수가 높은 학생 ID 반환 */
        if (String.valueOf(mGender).trim().equals("male")) {
            temp = students[mGrade - 1][0];
            temp.add(student);
            Collections.sort(temp); // 오름차순 정렬
            mostHighStudent = temp.get(temp.size() - 1);

        } else {
            temp = students[mGrade - 1][1];
            temp.add(student);
            Collections.sort(temp); // 오름차순 정렬
            mostHighStudent = temp.get(temp.size() - 1);
        }
        return mostHighStudent.mId;
    }

    /**
     * 학생 ID가 mId인 학생의 기록을 삭제한다.
     * 시스템에 mId 학생의 점수가 기록되어 있지 않다면, 0을 반환한다.
     * 삭제 후에 mId 학생의 학년과 성별이 동일한 학생 중에서 점수가 가장 낮은 학생의 ID를 반환한다.
     * 점수가 가장 낮은 학생이 여러 명이라면, 그 중에서 ID가 가장 작은 값을 반환한다.
     * 삭제 후에, 학년과 성별이 동일한 학생이 없다면, 0을 반환한다.
     *
     * @param mId
     * @return
     */
    public int remove(int mId) {
        ArrayList<Student> found = null;
        int result = 0;

        for (int i = 0; i < GRADE; i++) {
            for (int j = 0; j < GENDER; j++) {
                found = students[i][j];
                /* mId 제거 후 탐색 중단 */
                if (found.removeIf(s -> s.mId == mId)) {
                    // Collections.sort(found); // 오름차순 정렬
                    if (!found.isEmpty()) {
                        result = found.get(0).mId;
                    }
                    break;
                }
            }
        }
        return result;
    }

    /**
     * mGrade 학년 집합과 mGender 성별 집합에 속하면서, 점수가 mScore 이상인 학생 중에서 점수가 가장 낮은 학생의 ID를 반환한다.
     * 점수가 가장 낮은 학생이 여러 명이라면, 그 중에서 ID가 가장 작은 값을 반환한다.
     * mGradeCnt 개의 학년이 mGrade 배열로 주어진다. 예를 들어, 1학년과 3학년이라면 {1, 3}로 주어진다.
     * mGenderCnt 개의 성별이 mGender 배열로 주어진다. 예를 들어, 남성이라면 {“male”}로 주어지고, 남성과 여성이라면 {“male”, “female”}로 주어진다.
     * 점수가 mScore 이상인 학생이 없다면, 0을 반환한다.
     *
     * @param mGradeCnt
     * @param mGrade
     * @param mGenderCnt
     * @param mGender
     * @param mScore
     * @return
     */
    public int query(int mGradeCnt, int mGrade[], int mGenderCnt, char mGender[][], int mScore) {
        /* mGrade 집합 x mGender 집합 구하기 */
        ArrayList<Student> result = new ArrayList<>();
        List<Student> target;
        for (int i = 0; i < mGradeCnt; i++) {
            for (int j = 0; j < mGenderCnt; j++) {
                ArrayList<Student> temp;
                if (String.valueOf(mGender[j]).trim().equals("male")) {
                    temp = students[mGrade[i] - 1][0];
                } else {
                    temp = students[mGrade[i] - 1][1];
                }
                for(Student s : temp){
                    if(s.mScore >= mScore)
                        result.add(s);
                }
                // target = temp.stream().filter(s -> s.mScore >= mScore).collect(Collectors.toList());
                // result.addAll(target);
            }
        }
        Collections.sort(result);
        return !result.isEmpty() ? result.get(0).mId : 0;
    }

    /* 오히려 2ms 느려짐 */
    public List<Student> filteredSubset(ArrayList<Student> list, int mScore){
        List<Student> result = new ArrayList<>();
        for(Student s : list){
            if(s.mScore >= mScore){
                result.add(s);
            }
        }
        return result;
    }
}
