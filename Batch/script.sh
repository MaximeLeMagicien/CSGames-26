while IFS=, read -r customer_id name email age city subscription_type; do 
    if [[ -n "$customer_id" &&  -n "$name" &&  -n "$email" &&  -n "$age" && -n "$city" && -n "$subscription_type" ]]; then
        if [[ $age -gt 0 && $email =~ .*@.*\..* ]]; then
            echo $customer_id,$name,$email,$age,$city,$subscription_type;
        fi
    fi
done < "customer_profiles.csv"