package calculator;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

class StringCalculator {
    int add(String text) {
        // 문자열이 비었거나 null이면 0 반환
        if (text == null || text.isEmpty()) {
            return 0;
        }
        // 문자열의 숫자들을 보관할 배열 선언
        String[] numbers;

        // 커스텀 구분자
        if (text.startsWith("//")) {
            char customSeparator = text.charAt(2); // 커스텀 구분자는 // 뒤 문자
//            int newLineIndex = text.indexOf("\n"); // 줄바꿈 문자 인덱스 번호
            int newLineIndex = text.indexOf("\\n"); // \n 대신 \\n으로 작성해야 컴파일러는 첫번째 \, 두번째 \ 신호를 인식함.

            // 만약 '\\n'을 찾지 못했다면 문제있는 입력으로 처리
            if (newLineIndex == -1) {
                throw new IllegalArgumentException("Custom separator format is invalid.");
            }

            String numberString = text.substring(newLineIndex + 2); // 줄바꿈 문자 다음 인덱스부터 문자열 반환

            // 커스텀 구분자 char -> String으로 형변환 후 자름
            // numbers = numberString.split(String.valueOf(customSeparator));
            // String의 split() 메소드가 구분자를 단순한 글자가 아닌 정규표현식으로 인식 -> Pattern.quote()로 해결
            numbers = numberString.split(Pattern.quote(String.valueOf(customSeparator)));

        } else { // 쉼표(,) 또는 콜론(:)을 구분자로 가지는 경우
            String basicSeperator = text.replace(':', ','); // 기본 구분자 통일
            numbers = basicSeperator.split(",");
        }

        int sum = 0;

        for (String s : numbers) {
            if (s.isEmpty()) {
                continue;
            }
            int number;
            try {
                number = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("no char. only number");
            }

            if (number < 0) {
                throw new IllegalArgumentException("negative number error");
            }
            sum += number;
        }
        return sum;
    }
}

public class Application {
    public static void main(String[] args) throws IOException {
        // TODO: 프로그램 구현
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringCalculator calculator = new StringCalculator(); // 객체 생성

        int result;

        System.out.println("input String: ");
        String input = br.readLine();

        try {
            result = calculator.add(input);
            System.out.println("result : " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
