
import React from 'react';
import { Card, CardContent } from '@/components/ui/card';
import { Star } from 'lucide-react';

const testimonials = [
  {
    name: 'Maria Popescu',
    role: 'Student',
    avatar: 'photo-1494790108755-2616b612b786',
    rating: 5,
    text: "EcoMeal has changed how I eat! I get amazing food from my favorite bakery at half price, and I feel good about reducing waste. It's a win-win!"
  },
  {
    name: 'Alexandru Dumitru',
    role: 'Software Developer',
    avatar: 'photo-1472099645785-5658abf4ff4e',
    rating: 5,
    text: "Perfect for my busy lifestyle. I can grab fresh food on my way home from work and save money while helping the environment. Highly recommend!"
  },
  {
    name: 'Elena Ionescu',
    role: 'Teacher',
    avatar: 'photo-1438761681033-6461ffad8d80',
    rating: 5,
    text: "I've saved over 200€ in just 3 months! The variety is amazing - from pastries to gourmet meals. My kids love the surprise bags too."
  }
];

export const TestimonialsSection = () => {
  return (
    <section id="testimonials" className="py-20 bg-gray-50">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="text-center mb-16">
          <h2 className="text-4xl font-bold text-gray-900 mb-4">
            What Our Users Say
          </h2>
          <p className="text-xl text-gray-600 max-w-3xl mx-auto">
            Join thousands of happy users who are saving money and fighting food waste every day.
          </p>
        </div>

        <div className="grid md:grid-cols-3 gap-8">
          {testimonials.map((testimonial, index) => (
            <Card key={index} className="border-0 shadow-lg hover:shadow-xl transition-all duration-300">
              <CardContent className="p-8">
                {/* Rating */}
                <div className="flex mb-4">
                  {[...Array(testimonial.rating)].map((_, i) => (
                    <Star key={i} size={20} className="fill-yellow-400 text-yellow-400" />
                  ))}
                </div>

                {/* Testimonial text */}
                <p className="text-gray-600 mb-6 italic leading-relaxed">
                  "{testimonial.text}"
                </p>

                {/* User info */}
                <div className="flex items-center">
                  <img
                    src={`https://images.unsplash.com/${testimonial.avatar}?w=60&h=60&fit=crop&crop=face`}
                    alt={testimonial.name}
                    className="w-12 h-12 rounded-full object-cover mr-4"
                  />
                  <div>
                    <div className="font-semibold text-gray-900">{testimonial.name}</div>
                    <div className="text-sm text-gray-500">{testimonial.role}</div>
                  </div>
                </div>
              </CardContent>
            </Card>
          ))}
        </div>
      </div>
    </section>
  );
};
