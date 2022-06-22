package africa.semicolon.trueCaller.data.models;

import lombok.*;

@Data
@RequiredArgsConstructor
public class Contact {
    private int id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String[] phoneNumber;
    private int numberOfPhoneNumbers;

    public Contact(){
        phoneNumber = new String[3];
    }

    public void setPhoneNumber(String number){
        if (numberOfPhoneNumbers <= 3){
            phoneNumber[numberOfPhoneNumbers] = number;
            numberOfPhoneNumbers++;
        } else throw new ArrayIndexOutOfBoundsException("Exceeded Limit. Replace an existing phone number");
    }

    public String getPhoneNumber(int index){
        return phoneNumber[index - 1];
    }

    public void replaceNumber(int index, String number){
        phoneNumber[index - 1] = number;
    }
}