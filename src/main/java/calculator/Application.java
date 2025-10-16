package calculator;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
            int newLineIndex = text.indexOf("\n"); // 줄바꿈 문자 인덱스 번호
            String numberString = text.substring(newLineIndex + 1); // 줄바꿈 문자 다음 인덱스부터 문자열 반환

            // 커스텀 구분자 char -> String으로 형변환 후 자름
            numbers = numberString.split(String.valueOf(customSeparator));

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
                throw new IllegalArgumentException("입력값에 숫자가 아닌 문자가 포함되어 있습니다.");
            }

            if (number < 0) {
                throw new IllegalArgumentException("음수는 요구사항에 맞지 않는 입력값입니다.");
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

        System.out.println("덧셈할 문자열을 입력해주세요. ");
        String input = br.readLine();

        try {
            result = calculator.add(input);
            System.out.println("결과 : " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("에러: " + e.getMessage());
        }
    }
}
