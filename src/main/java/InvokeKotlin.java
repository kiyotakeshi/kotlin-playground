import com.kiyotakeshi.classes.Course;
import com.kiyotakeshi.classes.CourseCategory;
import com.kiyotakeshi.classes.CourseKt;

public class InvokeKotlin {

    public static void main(String[] args) {
        var course = new Course(1, "kotlin first step", "mike popcorn", CourseCategory.DEVELOPMENT);

        System.out.println("course : " + course);
    }
}
