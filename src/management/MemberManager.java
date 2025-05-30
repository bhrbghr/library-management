package management;

import datastructures.maps.CustomHashMap;
import library.Member;
import library.Transaction;

public class MemberManager {
    private CustomHashMap<String, Member> members;

    public MemberManager() {
        this.members = new CustomHashMap<>();
    }

    public void addMember(Member member) {
        members.put(member.getMemberId(), member);
    }

    public Member getMember(String memberId) {
        if (members.isEmpty()) {
            throw new IllegalStateException("No members registered.");
        }
        Member member = members.get(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member with ID " + memberId + " not found.");
        }
        return member;
    }

    public void recordTransaction(String memberId, Transaction transaction) {
        Member member = getMember(memberId);
        member.addTransaction(transaction);
    }

    public Transaction getLastTransaction(String memberId) {
        Member member = getMember(memberId);
        return member.getLastTransaction();
    }
}
